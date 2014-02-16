package br.ufs.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;

import br.ufs.livraria.modelo.Compra;
import br.ufs.livraria.modelo.Usuario;

@Stateless
public class CompraDAO extends DAO<Compra> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public CompraDAO() {
		super(Compra.class);
	}
	
	@Override
	public void remover(Compra compra) {
		remover(compra.getId());
	}
	
	public List<Compra> listar(Usuario usuario) {
		return entityManager.createQuery(
				"SELECT entidade FROM " + Compra.class.getSimpleName()
						+ " entidade"+
						"WHERE cliente_id = "+usuario.getId(), Compra.class).getResultList();
	}
	
	//Compras com Pagamentos Aprovados
	public List<Compra> aprovados() {
		// TODO Revisar, a classe Pagamento foi extinta
		/*return entityManager.createQuery(
				"SELECT entidade "+
		        "FROM " + Compra.class.getSimpleName()+" entidade "+
			    "JOIN "+Pagamento.class.getSimpleName()+" pagamento"+
				"ON entidade.pagamento_id = pagamento.id"+
				"WHERE pagamento.aprovado = 1", Compra.class).getResultList();*/
		return null;
	}
	
}