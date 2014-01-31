package br.ufs.livraria.dao;

import java.util.List;

import br.ufs.livraria.modelo.Pagamento;
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
				"SELECT entidade FROM " + Venda.class.getSimpleName()
						+ " entidade"+
						"WHERE cliente_id = "+usuario.getId(), Venda.class).getResultList();
	}
	
	//Compras com Pagamentos Aprovados
	public List<Venda> aprovados() {
		return entityManager.createQuery(
				"SELECT entidade "+
		        "FROM " + Venda.class.getSimpleName()+" entidade "+
			    "JOIN "+Pagamento.class.getSimpleName()+" pagamento"+
				"ON entidade.pagamento_id = pagamento.id"+
				"WHERE pagamento.aprovado = 1", Venda.class).getResultList();
	}
	
	//Compras com Pagamentos Aprovados
	public List<Venda> negados() {
		return entityManager.createQuery(
				"SELECT entidade "+
		        "FROM " + Venda.class.getSimpleName()+" entidade "+
			    "JOIN "+Pagamento.class.getSimpleName()+" pagamento"+
				"ON entidade.pagamento_id = pagamento.id"+
				"WHERE pagamento.aprovado = 1", Venda.class).getResultList();
	}
}
