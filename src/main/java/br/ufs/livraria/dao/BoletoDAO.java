package br.ufs.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;

import br.ufs.livraria.enumeration.StatusPagamento;
import br.ufs.livraria.modelo.Boleto;
import br.ufs.livraria.modelo.Cliente;

@Stateless
public class BoletoDAO extends DAO<Boleto> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public BoletoDAO() {
		super(Boleto.class);
	}

	@Override
	public void remover(Boleto boleto) {
		remover(boleto.getId());
	}
	
	public List<Boleto> listar(Cliente cliente){
		return entityManager.createQuery(
				"SELECT boleto FROM Boleto boleto"
				+ " WHERE (boleto.cliente.id = :cliente)", Boleto.class)
				.setParameter("cliente", cliente.getId())
				.getResultList();
	}

	public List<Boleto> confirmados(Cliente cliente) {
		return entityManager.createQuery(
				"SELECT boleto FROM Boleto boleto"
				+ " WHERE ((boleto.statusPagamento = :confirmado) AND "
				+ "(boleto.cliente.id = :cliente))", Boleto.class)
				.setParameter("confirmado", StatusPagamento.CONFIRMADO)
				.setParameter("cliente", cliente.getId())
				.getResultList();
	}

	public List<Boleto> cancelados(Cliente cliente) {
		return entityManager.createQuery(
				"SELECT boleto FROM Boleto boleto"
				+ " WHERE ((boleto.statusPagamento = :cancelado) AND "
				+ "(boleto.cliente.id = :cliente))", Boleto.class)
				.setParameter("cancelado", StatusPagamento.CANCELADO)
				.setParameter("cliente", cliente.getId())
				.getResultList();
	}

	public List<Boleto> emAndamento(Cliente cliente) {
		return entityManager.createQuery(
				"SELECT boleto FROM Boleto boleto"
				+ " WHERE ((boleto.statusPagamento = :emAndamento) AND "
				+ "(boleto.cliente.id = :cliente))", Boleto.class)
				.setParameter("emAndamento", StatusPagamento.EM_ANDAMENTO)
				.setParameter("cliente", cliente.getId())
				.getResultList();
	}
}