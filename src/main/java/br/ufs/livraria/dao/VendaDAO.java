package br.ufs.livraria.dao;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.ejb.Stateless;

import br.ufs.livraria.enumeration.StatusPagamento;
import br.ufs.livraria.modelo.Cliente;
import br.ufs.livraria.modelo.Venda;

@Stateless
public class VendaDAO extends DAO<Venda> implements Serializable {
	private static final long serialVersionUID = 1L;

	public VendaDAO() {
		super(Venda.class);
	}
	
	@Override
	public void remover(Venda venda) {
		remover(venda.getId());
	}
	
	public List<Venda> listar(Cliente cliente) {
		return entityManager.createQuery(
				"SELECT venda FROM Venda venda WHERE venda.cliente.id = :cliente", Venda.class)
				.setParameter("cliente", cliente.getId())
				.getResultList();
	}
	
	// Vendas com pagamento confirmado
	public List<Venda> confirmadas() {
		return entityManager.createQuery(
				"SELECT boleto.venda FROM Boleto boleto"
				+ " WHERE boleto.statusPagamento = :confirmado", Venda.class)
				.setParameter("confirmado", StatusPagamento.CONFIRMADO)
				.getResultList();
	}
	
	// Vendas por período
	public List<Venda> porPeriodo(Date data1, Date data2) {
		return entityManager.createQuery(
				"SELECT boleto.venda FROM Boleto boleto"
				+ " WHERE boleto.dataPagamento BETWEEN :data1 AND :data2", Venda.class)
				.setParameter("data1", data1)
				.setParameter("data2", data2)
				.getResultList();
	}
	
	// Vendas canceladas
	public List<Venda> canceladas() {
		return entityManager.createQuery(
				"SELECT boleto.venda FROM Boleto boleto"
				+ " WHERE boleto.statusPagamento = :cancelado", Venda.class)
				.setParameter("cancelado", StatusPagamento.CANCELADO)
				.getResultList();
	}
	
	// Vendas com pagamento confirmado, restringida por cliente
	public List<Venda> confirmadas(Cliente cliente) {
		return entityManager.createQuery(
				"SELECT boleto.venda FROM Boleto boleto"
				+ " WHERE ((boleto.statusPagamento = :confirmado) AND "
				+ "(boleto.venda.cliente.id = :cliente))", Venda.class)
				.setParameter("confirmado", StatusPagamento.CONFIRMADO)
				.setParameter("cliente", cliente.getId())
				.getResultList();
	}
		
	// Vendas canceladas, restringidas por cliente
	public List<Venda> canceladas(Cliente cliente) {
		return entityManager.createQuery(
				"SELECT boleto.venda FROM Boleto boleto"
				+ " WHERE ((boleto.statusPagamento = :cancelado) AND "
				+ "(boleto.venda.cliente.id = :cliente))", Venda.class)
				.setParameter("cancelado", StatusPagamento.CANCELADO)
				.setParameter("cliente", cliente.getId())
				.getResultList();
	}
	
	// Vendas em andamento, restringidas por cliente
	public List<Venda> emAndamento(Cliente cliente) {
		return entityManager.createQuery(
				"SELECT boleto.venda FROM Boleto boleto"
				+ " WHERE ((boleto.statusPagamento = :emAndamento) AND "
				+ "(boleto.venda.cliente.id = :cliente))", Venda.class)
				.setParameter("emAndamento", StatusPagamento.EM_ANDAMENTO)
				.setParameter("cliente", cliente.getId())
				.getResultList();
	}

	public List<Venda> pendentes() {
		return entityManager.createQuery(
				"SELECT boleto.venda FROM Boleto boleto"
				+ " WHERE boleto.statusPagamento = :pendente", Venda.class)
				.setParameter("pendente", StatusPagamento.EM_ANDAMENTO)
				.getResultList();
	}
	
	public HashMap<String, String> vendasDaSemana() {
		final int DIAS_DA_SEMANA = 7;
		
		String sql = "SELECT COALESCE(SUM(preco_efetivo * quantidade), 0) FROM itemlivro";
		sql += " INNER JOIN movimentacao ON movimentacao.id = itemlivro.movimentacao_id";
		sql += " WHERE tipo = 2 AND data::date = ";
		
		DateFormat formatoBD = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat formatoUsuario = new SimpleDateFormat("dd/MM");
		
		LinkedHashMap<String, String> vendas = new LinkedHashMap<String, String>(DIAS_DA_SEMANA);
		
		Calendar calendario = Calendar.getInstance();
		calendario.add(Calendar.DATE, -DIAS_DA_SEMANA);
		
		for (int dia = 0; dia < DIAS_DA_SEMANA; dia++) {
			calendario.add(Calendar.DATE, 1);
			String dataFormatoBD = formatoBD.format(calendario.getTime()).toString();
			String query = sql + "'" + dataFormatoBD + "';";
			Double totalVendido = (Double) entityManager.createNativeQuery(query).getSingleResult();
			String dataFormatoUsuario = formatoUsuario.format(calendario.getTime()).toString();
			vendas.put(dataFormatoUsuario, totalVendido.toString());
		}
		
		return vendas;
	}

}
