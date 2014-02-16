package br.ufs.livraria.dao;

import java.io.Serializable;

import javax.ejb.Stateless;

import br.ufs.livraria.modelo.Boleto;

@Stateless
public class BoletoDAO extends DAO<Boleto> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public BoletoDAO() {
		super(Boleto.class);
	}

	@Override
	public void remover(Boleto boleto) {
		remover(boleto.getId());
	}
	
}