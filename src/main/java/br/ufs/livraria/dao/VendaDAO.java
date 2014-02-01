package br.ufs.livraria.dao;

import java.util.List;

import br.ufs.livraria.modelo.Usuario;
import br.ufs.livraria.modelo.Venda;

public class VendaDAO extends DAO<Venda>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VendaDAO() {
		// TODO Auto-generated constructor stub
		super(Venda.class);
	}
	
	public void remover(Venda venda) {
		// TODO Auto-generated method stub
		entityManager.remove(entityManager.find(Venda.class,
				venda.getId()));
	}
	
	public List<Venda> listar(Usuario usuario) {
		return entityManager.createQuery(
				"SELECT entidade FROM Venda entidade "+
						"WHERE entidade.cliente_id = :usuario", Venda.class)
						.setParameter("usuario", usuario.getId())
						.getResultList();
	}
	
	//Compras com Pagamentos Aprovados
	public List<Venda> aprovados() {
		return entityManager.createQuery(
				"SELECT entidade FROM Venda entidade JOIN Pagamento pagamento "+
						"ON entidade.pagamento_id = pagamento.id "+
						"WHERE pagamento.aprovado = TRUE", Venda.class).getResultList();
	}
	
	//Compras com Pagamentos Negados
	public List<Venda> negados() {
		return entityManager.createQuery(
				"SELECT entidade FROM Venda entidade JOIN Pagamento pagamento "+
						"ON entidade.pagamento_id = pagamento.id "+
						"WHERE pagamento.aprovado = FALSE", Venda.class).getResultList();
	}
	
	//Compras com Pagamentos Aprovados por usuário
	public List<Venda> aprovados(Usuario usuario) {
		return entityManager.createQuery(
				"SELECT v FROM Venda v JOIN Pagamento p ON v.pagamento_id = p.id "
						+ "WHERE ((p.aprovado = TRUE) AND (v.cliente_id = :usuario))", Venda.class)
						.setParameter("usuario", usuario.getId())
						.getResultList();
	}
		
	//Compras com Pagamentos Negados por usuário
	public List<Venda> negados(Usuario usuario) {
		return entityManager.createQuery(
				"SELECT v FROM Venda v JOIN Pagamento p ON v.pagamento_id = p.id "
						+ "WHERE ((p.aprovado = FALSE) AND (v.cliente_id = :usuario))", Venda.class)
						.setParameter("usuario", usuario.getId())
						.getResultList();
	}
}
