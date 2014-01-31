package br.ufs.livraria.mb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufs.livraria.dao.LivroDAO;
import br.ufs.livraria.enumeration.MensagemTipo;
import br.ufs.livraria.modelo.Livro;

@Named
@RequestScoped
public class LivroMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private Livro livro;
	
	@EJB
	private LivroDAO livroDao;
	
	private Integer id;
	
	@Inject
	private MensagensMB mensagensMb;
	
	public LivroMB() {
		livro = new Livro();
		id = null;
	}
	
	/* Getters e setters */
	
	public Livro getFornecedor() {
		return livro;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
		if (id != null) {
			livro = livroDao.buscar(id);
		}
	}
	
	public List<Livro> getListaDeLivros() {
		return livroDao.listar();
	}
	
	/* Ações */
	
	public String atualizar() {
		try {
			livroDao.atualizar(livro);
			mensagensMb.adicionarMensagem(MensagemTipo.SUCCESSO, "O livro foi atualizado com sucesso!");
			return "index.jsf?faces-redirect=true";
		} catch (Exception excecao) {
			mensagensMb.adicionarMensagem(MensagemTipo.ERRO, "Ocorreu um erro durante o processamento da solicitação.");
			return "cadastro.jsf";
		}
	}
	
	public String excluir() {
		try {
			livroDao.remover(id);
			mensagensMb.adicionarMensagem(MensagemTipo.SUCCESSO, "O livro foi excluído com sucesso!");
			return "index.jsf?faces-redirect=true";
		} catch (Exception e) {
			mensagensMb.adicionarMensagem(MensagemTipo.ERRO, "Ocorreu um erro durante o processamento da solicitação.");
			return "index.jsf";
		}
	}
	
	public String inserir() {
		try {
			livroDao.inserir(livro);
			mensagensMb.adicionarMensagem(MensagemTipo.SUCCESSO, "O livro foi cadastrado com sucesso!");
			return "index.jsf?faces-redirect=true";
		} catch (Exception excecao) {
			mensagensMb.adicionarMensagem(MensagemTipo.ERRO, "Ocorreu um erro durante o processamento da solicitação.");
			return "cadastro.jsf";
		}
	}
	
	/* Outros */
	
	public boolean isCadastro() {
		return id == null;
	}
	
}