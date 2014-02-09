package br.ufs.livraria.mb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufs.livraria.dao.LivroDAO;
import br.ufs.livraria.enumeration.MensagemTipo;
import br.ufs.livraria.modelo.Livro;

@Named
@SessionScoped
public class LivroMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private Livro livro;
	
	@EJB
	private LivroDAO livroDao;
	
	@Inject
	private MensagensMB mensagensMb;
	
	public LivroMB() {
		livro = new Livro();
	}
	
	/* Getters e setters */
	
	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
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
			livroDao.remover(livro); // TODO Verificar
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
		return (livro.getId() == null);
	}
	
}