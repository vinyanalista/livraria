package br.ufs.livraria.dao;

import java.io.Serializable;

import javax.ejb.Stateless;

import br.ufs.livraria.modelo.Cliente;

@Stateless
public class ClienteDAO extends DAO<Cliente> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public ClienteDAO() {
		super(Cliente.class);
	}
}