package br.ufs.livraria.dao;

import java.io.Serializable;
import java.util.EnumSet;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.ufs.livraria.enumeration.BuscaFiltro;
import br.ufs.livraria.enumeration.BuscaOrdenacao;
import br.ufs.livraria.enumeration.Genero;
import br.ufs.livraria.modelo.Livro;

@Stateless
public class LivroDAO extends DAO<Livro> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private static final int estoqueEscasso = 5;

	public LivroDAO() {
		super(Livro.class);
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
	
	public List<Livro> buscar(String por, BuscaFiltro filtro, BuscaOrdenacao ordenacao, Genero genero) {
		StringBuilder jpql = new StringBuilder("SELECT livro FROM Livro livro");
		
		boolean filtrar = ((por != null) && (!por.isEmpty()) && (filtro != null));
		boolean filtrarGenero = ((genero != null) && (genero != Genero.TUDO));
		boolean ordenar = (ordenacao != null);

		if (filtrar || filtrarGenero) {
			jpql.append(" WHERE");
		}

		if (filtrar) {
			switch (filtro) {
			case TITULO:
				jpql.append(" (LOWER(livro.titulo) LIKE LOWER(:titulo))");
				break;
			case AUTOR:
				jpql.append(" (LOWER(livro.autor) LIKE LOWER(:autor))");
				break;
			case SINOPSE:
				jpql.append(" (LOWER(livro.sinopse) LIKE LOWER(:sinopse))");
				break;
			case TUDO:
			default:
				jpql.append(" ((LOWER(livro.titulo) LIKE LOWER(:titulo))");
				jpql.append(" OR (LOWER(livro.autor) LIKE LOWER(:autor))");
				jpql.append(" OR (LOWER(livro.sinopse) LIKE LOWER(:sinopse)))");
				break;
			}
		}
		
		if (filtrarGenero) {
			if (filtrar) {
				jpql.append(" AND");
			}
			jpql.append(" (livro.genero IN :genero)");
		}
		
		if (ordenar) {
			jpql.append(" ORDER BY");
			switch (ordenacao) {
			case ESTOQUE:
				jpql.append(" livro.estoque DESC, livro.titulo ASC");
				break;
			case MENOR_PRECO:
				jpql.append(" livro.preco ASC, livro.titulo ASC");
				break;
			case TITULO_A_Z:
				jpql.append(" livro.titulo ASC");
				break;
			case TITULO_Z_A:
				jpql.append(" livro.titulo DESC");
				break;
			default:
				break;
			}
		}
		
		TypedQuery<Livro> query = entityManager.createQuery(jpql.toString(), Livro.class);
		
		if (filtrar) {
			if (filtro.equals(BuscaFiltro.TITULO) || filtro.equals(BuscaFiltro.TUDO)) {
				query.setParameter("titulo", "%" + por + "%");
			}
			if (filtro.equals(BuscaFiltro.AUTOR) || filtro.equals(BuscaFiltro.TUDO)) {
				query.setParameter("autor", "%" + por + "%");
			}
			if (filtro.equals(BuscaFiltro.SINOPSE) || filtro.equals(BuscaFiltro.TUDO)) {
				query.setParameter("sinopse", "%" + por + "%");
			}
		}

		if (filtrarGenero) {
			query.setParameter("genero", EnumSet.of(genero));
		}

		return query.getResultList();
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

	@Override
	public void remover(Livro livro) {
		remover(livro.getId());
	}
	
}