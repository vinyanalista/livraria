package br.ufs.livraria.dao;

import br.ufs.livraria.modelo.Funcionario;

public class FuncionarioDAO extends DAO<Funcionario> {
	protected static final long serialVersionUID = 1L;
	
	public FuncionarioDAO() {
		super(Funcionario.class);
	}
}
