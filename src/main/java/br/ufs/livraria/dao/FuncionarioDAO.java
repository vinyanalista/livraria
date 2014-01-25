package br.ufs.livraria.dao;

import javax.ejb.Stateless;

import br.ufs.livraria.modelo.Funcionario;

@Stateless
public class FuncionarioDAO extends DAO<Funcionario> {
	private static final long serialVersionUID = 1L;
	
	public FuncionarioDAO() {
		super(Funcionario.class);
	}
}