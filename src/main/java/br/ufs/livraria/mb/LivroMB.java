package br.ufs.livraria.mb;

import java.io.*;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import br.ufs.livraria.dao.LivroDAO;
import br.ufs.livraria.enumeration.MensagemTipo;
import br.ufs.livraria.modelo.Livro;
import br.ufs.livraria.modelo.Mensagem;

@Named
@ViewScoped
public class LivroMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private Part capa;
	
	protected Integer id;
	
	private Livro livro;
	
	@EJB
	private LivroDAO livroDao;
	
	@Inject
	protected MensagensMB mensagensMb;
	
	public LivroMB() {
		livro = new Livro();
		id = null;
	}
	
	/* Getters e setters */

	public Part getCapa() {
        return capa;
    }
	
	public Integer getId() {
		return id;
	}
	
	public Livro getLivro() {
		return livro;
	}

	public void setCapa(Part capa) {
        this.capa = capa;
    }
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
	/* Ações */
	
	public String atualizar() {
		Mensagem mensagem;
		String proximaPagina = atribuirCapa();
		if (proximaPagina != null) {
			return proximaPagina;
		}
		try {
			livroDao.atualizar(livro);
			mensagem = new Mensagem(MensagemTipo.SUCCESSO,
					"O livro foi atualizado com sucesso!");
			proximaPagina = "index.jsf?faces-redirect=true";
		} catch (Exception excecao) {
			mensagem = new Mensagem(MensagemTipo.SUCCESSO,
					"Ocorreu um erro ao tentar atualizar o livro.");
			proximaPagina = "cadastro.jsf";
		}
		mensagensMb.adicionarMensagem(mensagem);
		return proximaPagina;
	}

	public void carregar() {
		if (id == null) {
			livro = new Livro();
		} else {
			livro = livroDao.buscar(id);
		}
	}
	
	public String excluir(Livro livro) {
		Mensagem mensagem;
		String proximaPagina;
		try {
			livroDao.remover(livro);
			mensagem = new Mensagem(MensagemTipo.SUCCESSO,
					"O livro foi excluído com sucesso!");
			proximaPagina = "index.jsf?faces-redirect=true";
		} catch (Exception excecao) {
			mensagem = new Mensagem(MensagemTipo.ERRO,
					"Ocorreu um erro ao tentar excluir o livro.");
			proximaPagina = "index.jsf";
		}
		mensagensMb.adicionarMensagem(mensagem);
		return proximaPagina;
	}
	
	public String inserir() {
		Mensagem mensagem;
		String proximaPagina = atribuirCapa();
		if (proximaPagina != null) {
			return proximaPagina;
		}
		try {
			livroDao.inserir(livro);
			mensagem = new Mensagem(MensagemTipo.SUCCESSO,
					"O livro foi cadastrado com sucesso!");
			proximaPagina = "index.jsf?faces-redirect=true";
		} catch (Exception excecao) {
			mensagem = new Mensagem(MensagemTipo.ERRO,
					"Ocorreu um erro ao tentar cadastrar o livro.");
			proximaPagina = "cadastro.jsf";
		}
		mensagensMb.adicionarMensagem(mensagem);
		return proximaPagina;
	}
	
	public List<Livro> listar() {
		return livroDao.listar();
	}
	
	/* Outros */
	
	// http://blog.gilliard.eti.br/2012/07/file-upload-no-jsf-2-2/
	// http://www.itcuties.com/j2ee/jsf-2-read-and-write-images-from-sql-database/
	// http://www.ramkitech.com/2013/06/file-upload-is-easy-in-jsf22.html
	// http://www.javatutorials.co.in/jsf-2-2-file-upload-example-using-hinputfile/
	// http://jsflive.wordpress.com/2013/04/23/jsf22-file-upload/

	private String atribuirCapa() {
		try {
            if (capa != null) {
				InputStream is = capa.getInputStream();
	            byte[] bytes = IOUtils.toByteArray(is);
	            livro.setCapa(bytes);
            }
            return null;
        } catch (Exception excecao){
            mensagensMb.adicionarMensagem(MensagemTipo.ERRO, "Houve um erro ao tentar fazer upload da capa.");
            return "cadastro.jsf";
        }
	}
	
	public boolean isCadastro() {
		return (id == null);
	}
	
}