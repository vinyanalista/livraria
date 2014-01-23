package br.ufs.livraria.dao;

import br.ufs.livraria.modelo.Fornecedor;

public class FornecedorDAO extends DAO<Fornecedor> {
	private static final long serialVersionUID = 1L;

	public FornecedorDAO() {
		super(Fornecedor.class);
	}

	@Override
	public void remover(Fornecedor fornecedor) {
		entityManager.remove(entityManager.find(Fornecedor.class,
				fornecedor.getId()));
	}

}