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
	
	public String montarUri() {
		return null;
	}
	
	public void processar() {
		// TODO Apenas para fins de teste! Remover!
		System.out.println("******************************************************************************************************");
		System.out.println("PARAMETROS DA BUSCA:\nPor: " + por + "\nFiltro: " + filtro + "\nGênero: " + genero + "\nOrdenação: " + ordenacao);
		System.out.println("******************************************************************************************************");
		// TODO Implementar geração dos resultados da busca
		lista = livroDao.buscar(por, null, null, null);
		if (lista.isEmpty()) {
			mensagensMb.adicionarMensagem(MensagemTipo.INFO, "Infelizmente sua busca não trouxe resultados. Tente mudar os critérios de busca.");
		}
	}
	
}