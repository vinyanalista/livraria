package br.ufs.livraria.mb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.ufs.livraria.dao.FornecedorDAO;
import br.ufs.livraria.modelo.Fornecedor;

@ManagedBean
@ViewScoped
public class FornecedorMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean cadastro = true;

	private Fornecedor fornecedor;

	@EJB
	private FornecedorDAO fornecedorDao;

	private List<Fornecedor> fornecedores;

	private Integer idDoFornecedor;

	public void carregarFornecedor() {
		if (idDoFornecedor == null) {
			cadastro = true;
			fornecedor = new Fornecedor();
		} else {
			cadastro = false;
			fornecedor = fornecedorDao.buscar(idDoFornecedor);
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

	public void salvar() {
		throw new IllegalArgumentException("Nome: " + fornecedor.getNome());
	}

	public void setIdDoFornecedor(Integer idDoFornecedor) {
		this.idDoFornecedor = idDoFornecedor;
	}
}