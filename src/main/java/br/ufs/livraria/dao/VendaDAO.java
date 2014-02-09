package br.ufs.livraria.dao;

import java.util.List;

import javax.ejb.Stateless;

import br.ufs.livraria.modelo.Cliente;
import br.ufs.livraria.modelo.Venda;
import java.util.Date;

@Stateless
public class VendaDAO extends DAO<Venda>{
	private static final long serialVersionUID = 1L;

	public VendaDAO() {
		super(Venda.class);
	}
	
	public List<Venda> listar(Cliente cliente) {
		return entityManager.createQuery(
				"SELECT v FROM Venda v WHERE v.cliente.id = :cliente", Venda.class)
				.setParameter("cliente", cliente.getId())
				.getResultList();
	}
	
	//Compras com Pagamentos Aprovados
	public List<Venda> aprovados() {
		return entityManager.createQuery(
				"SELECT v FROM Venda v WHERE v.pagamento.aprovado = TRUE", Venda.class)
				.getResultList();
	}
	
	//Compras com Pagamentos Aprovados por Período
		public List<Venda> aprovados(Date data1, Date data2) {
			return entityManager.createQuery(
					"SELECT v FROM Venda v WHERE v.pagamento.date BETWEEN date1 AND date2", Venda.class)
					.getResultList();
		}
	
	//Compras com Pagamentos Negados
	public List<Venda> negados() {
		return entityManager.createQuery(
				"SELECT v FROM Venda v WHERE v.pagamento.aprovado = FALSE", Venda.class)
				.getResultList();
	}
	
	//Compras com Pagamentos Aprovados por cliente
	public List<Venda> aprovados(Cliente cliente) {
		return entityManager.createQuery(
				"SELECT v FROM Venda v WHERE ((v.pagamento.aprovado = TRUE) AND "
				+ "(v.cliente.id = :cliente))", Venda.class)
				.setParameter("cliente", cliente.getId())
				.getResultList();
	}
		
	//Compras com Pagamentos Negados por cliente
	public List<Venda> negados(Cliente cliente) {
		return entityManager.createQuery(
				"SELECT v FROM Venda v WHERE ((v.pagamento.aprovado = FALSE) AND "
				+ "(v.cliente.id = :cliente))", Venda.class)
				.setParameter("cliente", cliente.getId())
				.getResultList();
	}
	
	//Compras em andamento por cliente
	public List<Venda> emAndamento(Cliente cliente) {
		return entityManager.createQuery(
				"SELECT v FROM Venda v WHERE ((v.pagamento.aprovado = NULL) AND "
				+ "(v.cliente.id = :cliente))", Venda.class)
				.setParameter("cliente", cliente.getId())
				.getResultList();
	}
}
