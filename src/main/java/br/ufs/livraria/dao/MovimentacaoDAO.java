package br.ufs.livraria.dao;

import java.util.List;

import javax.ejb.Stateless;

import br.ufs.livraria.modelo.ItemLivro;
import br.ufs.livraria.modelo.Movimentacao;

@Stateless
public class MovimentacaoDAO extends DAO<Movimentacao> {
    
	private static final long serialVersionUID = 1L;
	
	public MovimentacaoDAO() {
		super(Movimentacao.class);
	}
	
	public List<Movimentacao> listar(ItemLivro itemlivro) {
		return entityManager.createQuery(
				"SELECT v FROM Movimentacao v WHERE v.itemlivro.id = :itemlivro", Movimentacao.class)
				.setParameter("itemlivro", itemlivro.getId())
				.getResultList();
	}
	
}
