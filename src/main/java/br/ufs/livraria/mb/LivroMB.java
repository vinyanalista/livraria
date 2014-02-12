package br.ufs.livraria.mb;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import br.ufs.livraria.dao.DAO;
import br.ufs.livraria.dao.LivroDAO;
import br.ufs.livraria.enumeration.CrudOperacaoTipo;
import br.ufs.livraria.enumeration.MensagemTipo;
import br.ufs.livraria.modelo.Livro;;

@Named
@ViewScoped
public class LivroMB extends Crud<Livro> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Livro livro;
	
	@EJB
	private LivroDAO livroDao;
	
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

	@Override
	protected Livro newEntidade() {
		return new Livro();
	}

	@Override
	protected void setEntidade(Livro livro) {
		this.livro = livro;
	}
	
	public void setLivro(Livro livro) {
		this.livro = livro;
	}
	
}