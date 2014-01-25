package br.ufs.livraria.mb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
//import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
//import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.ufs.livraria.dao.EnderecoDAO;
import br.ufs.livraria.dao.FornecedorDAO;
import br.ufs.livraria.enumeration.MensagemTipo;
import br.ufs.livraria.modelo.Fornecedor;
//import br.ufs.livraria.util.JSFMessages;

@ManagedBean
@ViewScoped
public class FornecedorMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean cadastro = true;

	private Fornecedor fornecedor;

	@EJB
	private EnderecoDAO enderecoDao;

	@EJB
	private FornecedorDAO fornecedorDao;

	private List<Fornecedor> fornecedores;

	private Integer idDoFornecedor;
	
	@Inject
	private MensagensMB mensagensMb;

	public void carregarFornecedor() {
		if (idDoFornecedor == null) {
			cadastro = true;
			fornecedor = new Fornecedor();
		} else {
			cadastro = false;
			fornecedor = fornecedorDao.buscar(idDoFornecedor);
		}
	}

	public String excluir(Integer id) {
		try {
			fornecedorDao.remover(id);
			fornecedores = fornecedorDao.listar();
			mensagensMb.adicionarMensagem(MensagemTipo.SUCCESSO, "O fornecedor foi excluído com sucesso!");
			/* JSFMessages.exibirMensagemAposRedirecionar(FacesMessage.SEVERITY_INFO,
					"O fornecedor foi excluído com sucesso!"); */
			return "index.jsf?faces-redirect=true";
		} catch (Exception excecao) {
			mensagensMb.adicionarMensagem(MensagemTipo.ERRO, "Ocorreu um erro durante o processamento da solicitação.");
			/* JSFMessages.exibirMensagem(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um erro durante o processamento da solicitação: "
							+ excecao.getMessage()); */
			return "index.jsf";
		}
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public List<Fornecedor> getFornecedores() {
		if (fornecedores == null) {
			fornecedores = fornecedorDao.listar();
		}
		return fornecedores;
	}

	public Integer getIdDoFornecedor() {
		return idDoFornecedor;
	}

	public boolean isCadastro() {
		return cadastro;
	}

	public String salvar() {
		try {
			if (cadastro) {
				enderecoDao.inserir(fornecedor.getEndereco());
				fornecedorDao.inserir(fornecedor);
				mensagensMb.adicionarMensagem(MensagemTipo.SUCCESSO, "O fornecedor foi cadastrado com sucesso!");
				/* JSFMessages.exibirMensagemAposRedirecionar(
						FacesMessage.SEVERITY_INFO,
						"O fornecedor foi cadastrado com sucesso!"); */
			} else {
				enderecoDao.atualizar(fornecedor.getEndereco());
				fornecedorDao.atualizar(fornecedor);
				mensagensMb.adicionarMensagem(MensagemTipo.SUCCESSO, "O fornecedor foi atualizado com sucesso!");
				/* JSFMessages.exibirMensagemAposRedirecionar(
						FacesMessage.SEVERITY_INFO,
						"O fornecedor foi atualizado com sucesso!"); */
			}
			return "index.jsf?faces-redirect=true";
		} catch (Exception excecao) {
			mensagensMb.adicionarMensagem(MensagemTipo.ERRO, "Ocorreu um erro durante o processamento da solicitação.");
			/* JSFMessages.exibirMensagem(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um erro durante o processamento da solicitação: "
							+ excecao.getMessage()); */
			return "cadastro.jsf";
		}
	}

	public void setIdDoFornecedor(Integer idDoFornecedor) {
		this.idDoFornecedor = idDoFornecedor;
	}
}