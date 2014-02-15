package br.ufs.livraria.mb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufs.livraria.dao.FornecedorDAO;
import br.ufs.livraria.enumeration.Estado;
import br.ufs.livraria.enumeration.MensagemTipo;
import br.ufs.livraria.modelo.Fornecedor;
import br.ufs.livraria.modelo.Mensagem;

@Named
@ViewScoped
public class FornecedorMB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Fornecedor fornecedor;
	
	protected Integer id;
	
	@EJB
	private FornecedorDAO fornecedorDao;
	
	@Inject
	protected MensagensMB mensagensMb;
	
	public FornecedorMB() {
		fornecedor = new Fornecedor();
		id = null;
	}
	
	/* Getters e setters */

	public Fornecedor getFornecedor() {
		return fornecedor;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	/* Ações */

	public String atualizar() {
		Mensagem mensagem;
		String proximaPagina;
		try {
			fornecedorDao.atualizar(fornecedor);
			mensagem = new Mensagem(MensagemTipo.SUCCESSO,
					"O fornecedor foi atualizado com sucesso!");
			proximaPagina = "index.jsf?faces-redirect=true";
		} catch (Exception excecao) {
			mensagem = new Mensagem(MensagemTipo.ERRO,
					"Ocorreu um erro ao tentar atualizar o fornecedor.");
			proximaPagina = "cadastro.jsf";
		}
		mensagensMb.adicionarMensagem(mensagem);
		return proximaPagina;
	}

	public void carregar() {
		if (id == null) {
			fornecedor = new Fornecedor();
		} else {
			fornecedor = fornecedorDao.buscar(id);
		}
	}
	
	public String excluir(Fornecedor fornecedor) {
		Mensagem mensagem;
		String proximaPagina;
		try {
			fornecedorDao.remover(fornecedor);
			mensagem = new Mensagem(MensagemTipo.SUCCESSO,
					"O fornecedor foi excluído com sucesso!");
			proximaPagina = "index.jsf?faces-redirect=true";
		} catch (Exception excecao) {
			mensagem = new Mensagem(MensagemTipo.ERRO,
					"Ocorreu um erro ao tentar excluir o fornecedor.");
			proximaPagina = "index.jsf";
		}
		mensagensMb.adicionarMensagem(mensagem);
		return proximaPagina;
	}
	
	public String inserir() {
		Mensagem mensagem;
		String proximaPagina;
		try {
			fornecedorDao.inserir(fornecedor);
			mensagem = new Mensagem(MensagemTipo.SUCCESSO,
					"O fornecedor foi cadastrado com sucesso!");
			proximaPagina = "index.jsf?faces-redirect=true";
		} catch (Exception excecao) {
			mensagem = new Mensagem(MensagemTipo.ERRO,
					"Ocorreu um erro ao tentar cadastrar o fornecedor.");
			proximaPagina = "cadastro.jsf";
		}
		mensagensMb.adicionarMensagem(mensagem);
		return proximaPagina;
	}
	
		
	public List<Fornecedor> listar() {
		return fornecedorDao.listar();
	}

	/* Outros */

	public Estado[] getEstados() {
		return Estado.values();
	}
	
	public boolean isCadastro() {
		return (id == null);
	}
	
}