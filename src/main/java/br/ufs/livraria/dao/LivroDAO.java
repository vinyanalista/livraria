package br.ufs.livraria.dao;

import java.util.List;

import javax.ejb.Stateless;

import br.ufs.livraria.modelo.Livro;
import br.ufs.livraria.modelo.Usuario;

@Stateless
public class LivroDAO extends DAO<Livro> {
	private static final long serialVersionUID = 1L;
	
	private static final int estoqueEscasso = 5;

	public LivroDAO() {
		super(Livro.class);
		// TODO Auto-generated constructor stub
	}

	public int adicionarQtdEstoque(int id, int qtd){
		Livro l = entityManager.find(Livro.class, id);
		int estoqueAnterior = l.getEstoque();
		l.setEstoque(estoqueAnterior + qtd);
		this.atualizar(l);
		return estoqueAnterior + qtd;
	}
	
	public int removerQtdEstoque(int id, int qtd){
		Livro l = entityManager.find(Livro.class, id);
		int estoqueAnterior = l.getEstoque();
		l.setEstoque(estoqueAnterior - qtd);
		this.atualizar(l);
		return estoqueAnterior - qtd;
	}
	
	public List<Livro> buscarPadrao(String padrao){
		return entityManager.createQuery("select l from Livro l where l.titulo = "+padrao).getResultList();
	}
	
	public List<Livro> livrosEscassos(){
		return entityManager.createQuery("select l from Livro l where l.estoque <= " + estoqueEscasso).getResultList();
	}
	
}