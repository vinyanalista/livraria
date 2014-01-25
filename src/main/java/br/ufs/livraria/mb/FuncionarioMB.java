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
import br.ufs.livraria.dao.FuncionarioDAO;
import br.ufs.livraria.enumeration.MensagemTipo;
import br.ufs.livraria.modelo.Funcionario;
//import br.ufs.livraria.util.JSFMessages;

@ManagedBean
@ViewScoped
public class FuncionarioMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean cadastro = true;

	private Funcionario funcionario;

	@EJB
	private EnderecoDAO enderecoDao;

	@EJB
	private FuncionarioDAO funcionarioDao;

	private List<Funcionario> funcionarios;

	private Integer idDoFuncionario;
	
	@Inject
	private MensagensMB mensagensMb;

	public void carregarFuncionario() {
		if (idDoFuncionario == null) {
			cadastro = true;
			funcionario = new Funcionario();
		} else {
			cadastro = false;
			funcionario = funcionarioDao.buscar(idDoFuncionario);
		}
	}

	public String excluir(Integer id) {
		try {
			funcionarioDao.remover(id);
			funcionarios = funcionarioDao.listar();
			mensagensMb.adicionarMensagem(MensagemTipo.SUCCESSO, "O Funcionario foi excluído com sucesso!");
			/* JSFMessages.exibirMensagemAposRedirecionar(FacesMessage.SEVERITY_INFO,
					"O Funcionario foi excluído com sucesso!"); */
			return "index.jsf?faces-redirect=true";
		} catch (Exception excecao) {
			mensagensMb.adicionarMensagem(MensagemTipo.ERRO, "Ocorreu um erro durante o processamento da solicitação.");
			/* JSFMessages.exibirMensagem(FacesMessage.SEVERITY_ERROR,
					"Ocorreu um erro durante o processamento da solicitação: "
							+ excecao.getMessage()); */
			return "index.jsf";
		}
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		if (funcionarios == null) {
			funcionarios = funcionarioDao.listar();
		}
		return funcionarios;
	}

	public Integer getIdDoFuncionario() {
		return idDoFuncionario;
	}

	public boolean isCadastro() {
		return cadastro;
	}

	public String salvar() {
		try {
			if (cadastro) {
				enderecoDao.inserir(funcionario.getEndereco());
				funcionarioDao.inserir(funcionario);
				mensagensMb.adicionarMensagem(MensagemTipo.SUCCESSO, "O Funcionario foi cadastrado com sucesso!");
				/* JSFMessages.exibirMensagemAposRedirecionar(
						FacesMessage.SEVERITY_INFO,
						"O Funcionario foi cadastrado com sucesso!"); */
			} else {
				enderecoDao.atualizar(funcionario.getEndereco());
				funcionarioDao.atualizar(funcionario);
				mensagensMb.adicionarMensagem(MensagemTipo.SUCCESSO, "O Funcionario foi atualizado com sucesso!");
				/* JSFMessages.exibirMensagemAposRedirecionar(
						FacesMessage.SEVERITY_INFO,
						"O Funcionario foi atualizado com sucesso!"); */
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

	public void setIdDoFuncionario(Integer idDoFuncionario) {
		this.idDoFuncionario = idDoFuncionario;
	}
}