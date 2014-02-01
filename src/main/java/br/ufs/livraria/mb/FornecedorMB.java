package br.ufs.livraria.mb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufs.livraria.dao.FornecedorDAO;
import br.ufs.livraria.enumeration.MensagemTipo;
import br.ufs.livraria.modelo.Fornecedor;

@Named
@ViewScoped
public class FornecedorMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private Fornecedor fornecedor;
	
	@EJB
	private FornecedorDAO fornecedorDao;
	
	private Integer id;

	@Inject
	private MensagensMB mensagensMb;
	
	public FornecedorMB() {
		fornecedor = new Fornecedor();
		id = null;
	}
	
	/* Getters e setters */
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	public void carregarFornecedor() {
		if (id == null) {
			fornecedor = new Fornecedor();
		} else {
			fornecedor = fornecedorDao.buscar(id);
		}
	}
	
	public String excluir(Integer id) {
		try {
			fornecedorDao.remover(id);
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
		return (id == null);
	}
	
}