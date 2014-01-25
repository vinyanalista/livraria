package br.ufs.livraria.dao;

import javax.ejb.Stateless;

import br.ufs.livraria.modelo.Fornecedor;

@Stateless
public class FornecedorDAO extends DAO<Fornecedor> {
	private static final long serialVersionUID = 1L;

	public FornecedorDAO() {
		super(Fornecedor.class);
	}

	public void remover(Fornecedor fornecedor) {
		entityManager.remove(entityManager.find(Fornecedor.class,
				fornecedor.getId()));
	}

}