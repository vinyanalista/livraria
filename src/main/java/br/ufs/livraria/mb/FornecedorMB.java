package br.ufs.livraria.mb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufs.livraria.dao.FornecedorDAO;
import br.ufs.livraria.enumeration.MensagemTipo;
import br.ufs.livraria.modelo.Fornecedor;

@Named
@RequestScoped
public class FornecedorMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private Fornecedor fornecedor;
	
	@EJB
	private FornecedorDAO fornecedorDao;
	
	@Inject
	private MensagensMB mensagensMb;
	
	public FornecedorMB() {
		fornecedor = new Fornecedor();
	}
	
	/* Getters e setters */
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Fornecedor> getListaDeFornecedores() {
		return fornecedorDao.listar();
	}
	
	/* Ações */
	
	public String atualizar() {
		try {
			fornecedorDao.atualizar(fornecedor);
			mensagensMb.adicionarMensagem(MensagemTipo.SUCCESSO, "O fornecedor foi atualizado com sucesso!");
			return "index.jsf?faces-redirect=true";
		} catch (Exception excecao) {
			mensagensMb.adicionarMensagem(MensagemTipo.ERRO, "Ocorreu um erro durante o processamento da solicitação.");
			return "cadastro.jsf";
		}
	}
	
	public String excluir() {
		try {
			fornecedorDao.remover(fornecedor);
			mensagensMb.adicionarMensagem(MensagemTipo.SUCCESSO, "O fornecedor foi excluído com sucesso!");
			return "index.jsf?faces-redirect=true";
		} catch (Exception e) {
			mensagensMb.adicionarMensagem(MensagemTipo.ERRO, "Ocorreu um erro durante o processamento da solicitação.");
			return "index.jsf";
		}
	}
	
	public String inserir() {
		try {
			fornecedorDao.inserir(fornecedor);
			mensagensMb.adicionarMensagem(MensagemTipo.SUCCESSO, "O fornecedor foi cadastrado com sucesso!");
			return "index.jsf?faces-redirect=true";
		} catch (Exception excecao) {
			mensagensMb.adicionarMensagem(MensagemTipo.ERRO, "Ocorreu um erro durante o processamento da solicitação.");
			return "cadastro.jsf";
		}
	}
	
	/* Outros */
	
	public boolean isCadastro() {
		return (fornecedor.getId() == null);
	}
	
}