package br.ufs.livraria.dao;

import java.io.Serializable;

import javax.ejb.Stateless;

import br.ufs.livraria.modelo.Funcionario;

@Stateless
public class FuncionarioDAO extends DAO<Funcionario> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public FuncionarioDAO() {
		super(Funcionario.class);
	}

	@Override
	public void remover(Funcionario funcionario) {
		remover(funcionario.getId());
	}
	
}