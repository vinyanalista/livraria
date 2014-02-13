package br.ufs.livraria.mb;

import java.io.*;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import br.ufs.livraria.dao.DAO;
import br.ufs.livraria.dao.LivroDAO;
import br.ufs.livraria.enumeration.CrudOperacaoTipo;
import br.ufs.livraria.enumeration.MensagemTipo;
import br.ufs.livraria.modelo.Livro;

@Named
@ViewScoped
public class LivroMB extends Crud<Livro> implements Serializable {
	private static final long serialVersionUID = 1L;

	private Part capa;
	
	private Livro livro;
	
	@EJB
	private LivroDAO livroDao;
	
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

	public String atualizar() {
		String redirecionar = atribuirCapa();
		if (redirecionar != null) {
			return redirecionar;
		} else {
			return super.atualizar();
		}
	}

	public Part getCapa() {
        return capa;
    }

	@Override
	protected DAO<Livro> getDao() {
		return livroDao;
	}
	
	@Override
	protected Livro getEntidade() {
		return livro;
	}
	
	public Livro getLivro() {
		return livro;
	}
		
	@Override
	protected String getMensagem(CrudOperacaoTipo operacao, MensagemTipo resultado) {
		switch (operacao) {
		case ATUALIZAR:
			switch (resultado) {
			case SUCCESSO:
				return "O livro foi atualizado com sucesso!";
			case ERRO:
				return "Ocorreu um erro ao tentar atualizar o livro.";
			default:
				return null;
			}
		case EXCLUIR:
			switch (resultado) {
			case SUCCESSO:
				return "O livro foi excluído com sucesso!";
			case ERRO:
				return "Ocorreu um erro ao tentar excluir o livro.";
			default:
				return null;
			}
		case INSERIR:
			switch (resultado) {
			case SUCCESSO:
				return "O livro foi cadastrado com sucesso!";
			case ERRO:
				return "Ocorreu um erro ao tentar cadastrar o livro.";
			default:
				return null;
			}
		default:
			return null;
		}
	}

	public String inserir() {
		String redirecionar = atribuirCapa();
		if (redirecionar != null) {
			return redirecionar;
		} else {
			return super.inserir();
		}
	}

	@Override
	protected Livro newEntidade() {
		return new Livro();
	}

	public void setCapa(Part capa) {
        this.capa = capa;
    }

	@Override
	protected void setEntidade(Livro livro) {
		this.livro = livro;
	}
	
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
}