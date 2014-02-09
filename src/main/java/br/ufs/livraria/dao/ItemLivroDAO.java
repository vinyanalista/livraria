package br.ufs.livraria.dao;

import javax.ejb.Stateless;

import br.ufs.livraria.modelo.ItemLivro;

@Stateless
public class ItemLivroDAO extends DAO<ItemLivro> {
	private static final long serialVersionUID = 1L;
	
	public ItemLivroDAO() {
		super(ItemLivro.class);
	}
}