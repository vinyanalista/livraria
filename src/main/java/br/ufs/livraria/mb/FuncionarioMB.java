package br.ufs.livraria.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;

import br.ufs.livraria.bean.LoginInfo;
import br.ufs.livraria.dao.FuncionarioDAO;
import br.ufs.livraria.enumeration.MensagemTipo;
import br.ufs.livraria.enumeration.PermissaoFuncionario;
import br.ufs.livraria.modelo.Funcionario;

@Named
@ViewScoped
public class FuncionarioMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private Funcionario funcionario;

	@EJB
	private FuncionarioDAO funcionarioDao;

	private Integer id;

	@Inject
	private MensagensMB mensagensMb;
	
	@Inject
	private LoginInfo loginInfo;
	
	private String senha1;
	private String senha2;

	public FuncionarioMB() throws IOException {
		funcionario = new Funcionario();
		id = null;
	}

	@PostConstruct
	public void init() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		if (request.getRequestURI().endsWith("cadastro.jsf") && request.getParameter("id") == null) {
			mensagensMb.adicionarMensagem(MensagemTipo.ADVERTENCIA, "Você não possui permissão para visualizar este registro");
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf?faces-redirect=true");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/* Getters e setters */

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) throws IOException {
		this.id = id;
		if (id != null) {
			Funcionario funcionarioLogado = (Funcionario) loginInfo.getUsuarioLogado();
			if (funcionarioLogado.getPermissao() == PermissaoFuncionario.ADMINISTRADOR.getValor() || id.equals(funcionarioLogado.getId())) {
				funcionario = funcionarioDao.buscar(id);
			} else {
				mensagensMb.adicionarMensagem(MensagemTipo.ADVERTENCIA, "Você não possui permissão para visualizar este registro");
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf?faces-redirect=true");
			}
		}
	}

	public List<Funcionario> getListaDeFuncionarios() {
		Funcionario funcionarioLogado = (Funcionario) loginInfo.getUsuarioLogado();
		if (funcionarioLogado.getPermissao() == PermissaoFuncionario.ADMINISTRADOR.getValor()) {
			return funcionarioDao.listar();
		}
		return Arrays.asList(funcionarioDao.buscar(funcionarioLogado.getId()));
	}

	/* Ações */

	public String atualizar() {
		try {
			if (senhasIguais()) {
				funcionario.setSenha(DigestUtils.sha1Hex(senha1));
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

	public String excluir(Funcionario funcionario) {
		try {
			funcionarioDao.remover(funcionario); // TODO Verificar
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
				funcionario.setSenha(DigestUtils.sha1Hex(senha1));
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