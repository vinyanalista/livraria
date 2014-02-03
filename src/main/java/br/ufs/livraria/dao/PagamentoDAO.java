package br.ufs.livraria.dao;

import java.util.List;

import br.ufs.livraria.modelo.Pagamento;
import br.ufs.livraria.modelo.Usuario;

public class PagamentoDAO extends DAO<Pagamento> {

	private static final long serialVersionUID = 1L;

	public PagamentoDAO() {
		super(Pagamento.class);
	}
	
	public List<Pagamento> pagamentosDoUsuario(Usuario usuario) {
		return entityManager.createQuery("select v.pagamento from Venda v where v.cliente = :usuario", Pagamento.class)
				.setParameter("usuario", usuario)
				.getResultList();
	}
}
