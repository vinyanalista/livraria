package br.ufs.livraria.dao;

import java.io.Serializable;

import javax.ejb.Stateless;

import br.ufs.livraria.modelo.Endereco;

@Stateless
public class EnderecoDAO extends DAO<Endereco> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public EnderecoDAO() {
		super(Endereco.class);
	}

	@Override
	public void remover(Endereco endereco) {
		remover(endereco.getId());
	}
	
}