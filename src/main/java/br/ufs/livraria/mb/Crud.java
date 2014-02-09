package br.ufs.livraria.mb;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufs.livraria.dao.DAO;
import br.ufs.livraria.enumeration.CrudOperacaoTipo;
import br.ufs.livraria.enumeration.MensagemTipo;
import br.ufs.livraria.modelo.Entidade;

@Named
@ViewScoped
public abstract class Crud<E extends Entidade> implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Integer id;

	@Inject
	protected MensagensMB mensagensMb;

	public Crud() {
		setEntidade(newEntidade());
		id = null;
	}

	/* Contrato */

	protected abstract DAO<E> getDao();
	
	protected abstract E getEntidade();
	
	protected String getMensagem(CrudOperacaoTipo operacao,
			MensagemTipo resultado) {
		switch (operacao) {
		case ATUALIZAR:
			switch (resultado) {
			case SUCCESSO:
				return "Atualização realizada com sucesso!";
			case ERRO:
				return "Ocorreu um erro durante o processamento da solicitação.";
			default:
				return null;
			}
		case EXCLUIR:
			switch (resultado) {
			case SUCCESSO:
				return "Exclusão realizada com sucesso!";
			case ERRO:
				return "Ocorreu um erro durante o processamento da solicitação.";
			default:
				return null;
			}
		case INSERIR:
			switch (resultado) {
			case SUCCESSO:
				return "Cadastro efetuado com sucesso!";
			case ERRO:
				return "Ocorreu um erro durante o processamento da solicitação.";
			default:
				return null;
			}
		default:
			return null;
		}
	}
	
	protected String getRedirecionamento(CrudOperacaoTipo operacao,
			MensagemTipo resultado) {
		switch (operacao) {
		case ATUALIZAR:
		case INSERIR:
			switch (resultado) {
			case SUCCESSO:
				return "index.jsf?faces-redirect=true";
			case ERRO:
				return "cadastro.jsf";
			default:
				return null;
			}
		case EXCLUIR:
			switch (resultado) {
			case SUCCESSO:
				return "index.jsf?faces-redirect=true";
			case ERRO:
				return "index.jsf";
			default:
				return null;
			}
		default:
			return null;
		}
	}

	protected abstract E newEntidade();

	protected abstract void setEntidade(E entidade);

	/* Getters e setters */

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/* Ações */

	public String atualizar() {
		MensagemTipo resultado = MensagemTipo.SUCCESSO;
		try {
			getDao().atualizar(getEntidade());
		} catch (Exception excecao) {
			resultado = MensagemTipo.ERRO;
		}
		mensagensMb.adicionarMensagem(resultado,
				getMensagem(CrudOperacaoTipo.ATUALIZAR, resultado));
		return getRedirecionamento(CrudOperacaoTipo.ATUALIZAR, resultado);
	}

	public void carregar() {
		if (id == null) {
			setEntidade(newEntidade());
		} else {
			setEntidade(getDao().buscar(id));
		}
	}

	public String excluir(E entidade) {
		MensagemTipo resultado = MensagemTipo.SUCCESSO;
		try {
			getDao().remover(entidade);
		} catch (Exception excecao) {
			resultado = MensagemTipo.ERRO;
		}
		mensagensMb.adicionarMensagem(resultado,
				getMensagem(CrudOperacaoTipo.EXCLUIR, resultado));
		return getRedirecionamento(CrudOperacaoTipo.EXCLUIR, resultado);
	}

	public String inserir() {
		MensagemTipo resultado = MensagemTipo.SUCCESSO;
		try {
			getDao().inserir(getEntidade());
		} catch (Exception excecao) {
			resultado = MensagemTipo.ERRO;
		}
		mensagensMb.adicionarMensagem(resultado,
				getMensagem(CrudOperacaoTipo.INSERIR, resultado));
		return getRedirecionamento(CrudOperacaoTipo.INSERIR, resultado);
	}
	
	public List<E> listar() {
		return getDao().listar();
	}

	/* Outros */

	public boolean isCadastro() {
		return (id == null);
	}

}