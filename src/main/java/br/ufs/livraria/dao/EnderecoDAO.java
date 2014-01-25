package br.ufs.livraria.dao;

import javax.ejb.Stateless;

import br.ufs.livraria.modelo.Endereco;

@Stateless
public class EnderecoDAO extends DAO<Endereco> {
	private static final long serialVersionUID = 1L;
	
	public EnderecoDAO() {
		super(Endereco.class);
	}
}