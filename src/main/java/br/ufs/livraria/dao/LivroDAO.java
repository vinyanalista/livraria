package br.ufs.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;

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
		// TODO Apenas para fins de teste! Remover!
		System.out.println("******************************************************************************************************");
		System.out.println("LIVRO DAO\nPor: " + por + "\nFiltro: " + filtro + "\nGênero: " + genero + "\nOrdenação: " + ordenacao);
		System.out.println("******************************************************************************************************");

		StringBuilder query = new StringBuilder("SELECT livro FROM Livro livro WHERE");
		if ((por != null) && (!por.isEmpty())) {
			switch (filtro) {
			case TITULO:
				query.append(" livro.titulo");
				break;
			// TODO Verificar demais casos
			case AUTOR:
			case SINOPSE:
			case TUDO:
			default:
				query.append(" livro.titulo");
				break;
			}
			query.append(" LIKE '%").append(por).append("%'");
		}
		// TODO Busca por gênero
		if (ordenacao != null) {
			query.append(" ORDER BY");
			switch (ordenacao) {
			case ESTOQUE:
				query.append(" livro.quantidade DESC");
				break;
			case MENOR_PRECO:
				query.append(" livro.preco ASC");
				break;
			case TITULO_A_Z:
				query.append(" livro.titulo ASC");
				break;
			case TITULO_Z_A:
				query.append(" livro.titulo DESC");
				break;
			default:
				break;
			}
		}
		return entityManager.createQuery(query.toString(), Livro.class).getResultList();
	}
	
}