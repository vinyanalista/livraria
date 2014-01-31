package br.ufs.livraria.mb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufs.livraria.dao.FuncionarioDAO;
import br.ufs.livraria.enumeration.MensagemTipo;
import br.ufs.livraria.modelo.Funcionario;

@Named
@RequestScoped
public class FuncionarioMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private Funcionario funcionario;

	@EJB
	private FuncionarioDAO funcionarioDao;

	private Integer id;

	@Inject
	private MensagensMB mensagensMb;

	private String senha1;
	private String senha2;

	public FuncionarioMB() {
		funcionario = new Funcionario();
		id = null;
	}

	/* Getters e setters */

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
		if (id != null) {
			funcionario = funcionarioDao.buscar(id);
		}
	}

	public List<Funcionario> getListaDeFuncionarioes() {
		return funcionarioDao.listar();
	}

	/* Ações */

	public String atualizar() {
		try {
			if (senhasIguais()) {
				funcionarioDao.atualizar(funcionario);
				mensagensMb.adicionarMensagem(MensagemTipo.SUCCESSO,
						"O Funcionario foi atualizado com sucesso!");
				return "index.jsf?faces-redirect=true";
			} else {
				mensagensMb.adicionarMensagem(MensagemTipo.ERRO,
						"A senha não confere");
				return null;
			}
		} catch (Exception excecao) {
			mensagensMb.adicionarMensagem(MensagemTipo.ERRO,
					"Ocorreu um erro durante o processamento da solicitação.");
			return "cadastro.jsf";
		}
	}

	public String excluir() {
		try {
			funcionarioDao.remover(id);
			mensagensMb.adicionarMensagem(MensagemTipo.SUCCESSO,
					"O Funcionario foi excluído com sucesso!");
			return "index.jsf?faces-redirect=true";
		} catch (Exception e) {
			mensagensMb.adicionarMensagem(MensagemTipo.ERRO,
					"Ocorreu um erro durante o processamento da solicitação.");
			return "index.jsf";
		}
	}

	public String inserir() {
		try {
			if (senhasIguais()) {
				funcionarioDao.inserir(funcionario);
				mensagensMb.adicionarMensagem(MensagemTipo.SUCCESSO,
						"O Funcionario foi cadastrado com sucesso!");
				return "index.jsf?faces-redirect=true";
			} else {
				mensagensMb.adicionarMensagem(MensagemTipo.ERRO,
						"A senha não confere");
				return null;
			}

		} catch (Exception excecao) {
			mensagensMb.adicionarMensagem(MensagemTipo.ERRO,
					"Ocorreu um erro durante o processamento da solicitação.");
			return "cadastro.jsf";
		}
	}

	/* Outros */

	private boolean senhasIguais() {
		return senha1.equals(senha2);
	}

	public boolean isCadastro() {
		return id == null;
	}

	public String getSenha1() {
		return senha1;
	}

	public void setSenha1(String senha1) {
		this.senha1 = senha1;
	}

	public String getSenha2() {
		return senha2;
	}

	public void setSenha2(String senha2) {
		this.senha2 = senha2;
	}

}