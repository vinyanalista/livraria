package br.ufs.livraria.mb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufs.livraria.dao.LivroDAO;
import br.ufs.livraria.enumeration.BuscaFiltro;
import br.ufs.livraria.enumeration.BuscaOrdenacao;
import br.ufs.livraria.enumeration.Genero;
import br.ufs.livraria.enumeration.MensagemTipo;
import br.ufs.livraria.modelo.Livro;

@Named
@ViewScoped
public class BuscarMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final Integer DEFAULT_FILTRO = BuscaFiltro.TUDO.getValue();
	private static final Integer DEFAULT_GENERO = null;
	private static final Integer DEFAULT_ORDENACAO = BuscaOrdenacao.TITULO_A_Z.getValue();
	private static final String DEFAULT_POR = "";

	private Integer filtro;
	private Integer genero;
	private Integer ordenacao;
	private String por;
	
	private List<Livro> lista;
	
	@EJB
	private LivroDAO livroDao;
	
	private String tituloDaPagina = "Resultados da busca por Mario Puzo";
	
	@Inject
	private MensagensMB mensagensMb;
	
	public BuscarMB() {
		filtro = DEFAULT_FILTRO;
		genero = DEFAULT_GENERO;
		ordenacao = DEFAULT_ORDENACAO;
		por = DEFAULT_POR;
	}
	
	/* Getters e setters */
	
	public Integer getFiltro() {
		return filtro;
	}
	
	public Integer getGenero() {
		return genero;
	}
	
	public Genero[] getGeneros() {
		return Genero.values();
	}
	
	public List<Livro> getLista() {
		return lista;
	}
	
	public BuscaFiltro[] getOpcoesBuscaFiltro() {
		return BuscaFiltro.values();
	}
	
	public BuscaOrdenacao[] getOpcoesBuscaOrdenacao() {
		return BuscaOrdenacao.values();
	}
	
	public String getPor() {
		return por;
	}
	
	public Integer getOrdenacao() {
		return ordenacao;
	}
	
	public String getTituloDaPagina() {
		return tituloDaPagina;
	}
	
	public void setFiltro(Integer filtro) {
		this.filtro = filtro;
	}
	
	public void setGenero(Integer genero) {
		this.genero = genero;
	}
	
	public void setOrdenacao(Integer ordenacao) {
		this.ordenacao = ordenacao;
	}
	
	public void setPor(String por) {
		this.por = por;
	}
	
	/* Ações */
	
	public void processar() {
		// TODO Apenas para fins de teste! Remover!
		System.out.println("******************************************************************************************************");
		System.out.println("BUSCAR MB\nPor: " + por + "\nFiltro: " + filtro + "\nGênero: " + genero + "\nOrdenação: " + ordenacao);
		System.out.println("******************************************************************************************************");

		// TODO Implementar geração dos resultados da busca

		String por = (this.por != null) ? this.por : DEFAULT_POR;

		BuscaFiltro filtro;
		if (this.filtro == null) {
			this.filtro = DEFAULT_FILTRO;
		}
		switch (this.filtro) {
			case 2:
				filtro = BuscaFiltro.TITULO;
				break;
			case 3:
				filtro = BuscaFiltro.AUTOR;
				break;
			case 4:
				filtro = BuscaFiltro.SINOPSE;
				break;
			case 1:
			default:
				filtro = BuscaFiltro.TUDO;
				break;
		}

		BuscaOrdenacao ordenacao;
		if (this.ordenacao == null) {
			this.ordenacao = DEFAULT_ORDENACAO;
		}
		switch (this.ordenacao) {
			case 2:
				ordenacao = BuscaOrdenacao.TITULO_Z_A;
				break;
			case 3:
				ordenacao = BuscaOrdenacao.ESTOQUE;
				break;
			case 4:
				ordenacao = BuscaOrdenacao.MENOR_PRECO;
				break;
			case 1:
			default:
				ordenacao = BuscaOrdenacao.TITULO_A_Z;
				break;
		}

		lista = livroDao.buscar(por, filtro, ordenacao, null);
	}
	
}