package br.ufs.livraria.mb;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.ufs.livraria.dao.DAO;
import br.ufs.livraria.dao.FornecedorDAO;
import br.ufs.livraria.enumeration.CrudOperacaoTipo;
import br.ufs.livraria.enumeration.MensagemTipo;
import br.ufs.livraria.modelo.Fornecedor;

@Named
@ViewScoped
public class FornecedorMB extends Crud<Fornecedor> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Fornecedor fornecedor;
	
	@EJB
	private FornecedorDAO fornecedorDao;
	
	@Override
	protected DAO<Fornecedor> getDao() {
		return fornecedorDao;
	}
	
	@Override
	protected Fornecedor getEntidade() {
		return fornecedor;
	}
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	
	@Override
	protected String getMensagem(CrudOperacaoTipo operacao,
			MensagemTipo resultado) {
		switch (operacao) {
		case ATUALIZAR:
			switch (resultado) {
			case SUCCESSO:
				return "O fornecedor foi atualizado com sucesso!";
			case ERRO:
				return "Ocorreu um erro ao tentar atualizar o fornecedor.";
			default:
				return null;
			}
		case EXCLUIR:
			switch (resultado) {
			case SUCCESSO:
				return "O fornecedor foi excluído com sucesso!";
			case ERRO:
				return "Ocorreu um erro ao tentar excluir o fornecedor.";
			default:
				return null;
			}
		case INSERIR:
			switch (resultado) {
			case SUCCESSO:
				return "O fornecedor foi cadastrado com sucesso!";
			case ERRO:
				return "Ocorreu um erro ao tentar cadastrar o fornecedor.";
			default:
				return null;
			}
		default:
			return null;
		}
	}

	@Override
	protected Fornecedor newEntidade() {
		return new Fornecedor();
	}

	@Override
	protected void setEntidade(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
}