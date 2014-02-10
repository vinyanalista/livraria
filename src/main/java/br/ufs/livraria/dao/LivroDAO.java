package br.ufs.livraria.dao;

import java.util.List;

import javax.ejb.Stateless;

import br.ufs.livraria.modelo.Livro;

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
	
	public List<Livro> maisVendidos(){
		String query = "SELECT livro FROM Livro livro";
		query += " LEFT JOIN ItemLivro itemlivro ON itemlivro.livro = livro";
		query += " INNER JOIN Venda venda ON venda = itemlivro.movimentacao";
		query += " GROUP BY livro";
		query += " ORDER BY SUM(itemlivro.quantidade) DESC, livro.titulo";
		return entityManager.createQuery(query, Livro.class).setMaxResults(8).getResultList();
	}
	
	public List<Livro> ultimosLancamentos(){
		return entityManager.createQuery("SELECT livro FROM Livro livro ORDER BY livro.id DESC", Livro.class).setMaxResults(6).getResultList();
	}
	
	public List<Livro> buscarLivro(String padrao){
		return entityManager.createQuery(
				"SELECT livro FROM Livro livro WHERE l.titulo  like '%padrao%'", Livro.class)
				.setParameter("padrao", padrao)
				.getResultList();
	}
	
	public List<Livro> livrosEscassos(){
		return entityManager.createQuery(
				"SELECT livro FROM Livro livro WHERE l.estoque <= " + estoqueEscasso, Livro.class)
				.getResultList();
	}
	
	public List<Livro> buscaPorCategoria(String categoria){
		return entityManager.createQuery(
				"SELECT livro FROM Livro livro WHERE l.categoria = categoria", Livro.class)
				.setParameter("categoria", categoria)
				.getResultList();
	}
	
	public List<Livro> buscaLivroPorEditora(String editora){
		return entityManager.createQuery(
				"SELECT livro FROM Livro livro WHERE l.editora  like '%editora%'", Livro.class)
				.setParameter("editora", editora)
				.getResultList();
	}
	
	public List<Livro> buscaLivroPorAno(int ano){
		return entityManager.createQuery(
				"SELECT livro FROM Livro livro WHERE l.ano = ano", Livro.class)
				.setParameter("ano", ano)
				.getResultList();
	}
	
	public List<Livro> buscaLivroPorPreco(float precoMin,float precoMax){
		return entityManager.createQuery(
				"SELECT livro FROM Livro livro WHERE l.preco BETWEEN precoMin AND precoMax", Livro.class)
				.setParameter("precoMin", precoMin)
				.setParameter("precoMax", precoMax)
				.getResultList();
	}
	
}