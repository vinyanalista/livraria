package br.ufs.livraria.mb;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import br.ufs.livraria.bean.LoginInfo;
import br.ufs.livraria.dao.UsuarioDAO;
import br.ufs.livraria.modelo.Cliente;
import br.ufs.livraria.modelo.Usuario;

@Named
@ViewScoped
public class LoginMB implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
	private String senha;
	
	private String urlRetorno;
	
	@EJB
	private UsuarioDAO usuarioDAO;
	
	@Inject
	private LoginInfo loginInfo;
	
	public String login() {
		try {
			if (usuarioDAO.verificarCredenciais(email, senha)) {
				addMessage("Login efetuado com sucesso", FacesMessage.SEVERITY_INFO);
				Usuario usuario = usuarioDAO.getByEmail(email);
				if (loginInfo.isLoggedIn()) {
					logout();
				}
				loginInfo.setUsuarioLogado(usuario);
				if (urlRetorno == null) {
					return "index.jsf?faces-redirect=true";
				} else {
					return urlRetorno;
				}
			} else {
				addMessage("Usuário ou senha inválidos", FacesMessage.SEVERITY_ERROR);
			}
		} catch (EJBException e) {
			if (e.getCause() instanceof NoResultException) {
				addMessage("Usuário ou senha inválidos", FacesMessage.SEVERITY_ERROR);
			} else {
				addMessage("Ocorreu um erro durante o processamento da solicitação.", FacesMessage.SEVERITY_ERROR);
				return "index.jsf?faces-redirect=true";
			}
		}
		return null;
	}
	
	public String logout() {
		Usuario usuarioLogado = loginInfo.getUsuarioLogado();
		loginInfo.setUsuarioLogado(null);
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if (session != null) {
			session.invalidate();
		}
		if (usuarioLogado instanceof Cliente) {
			return "/index.jsf?faces-redirect=true";
		} else {
			return "/funcionario/login.jsf?faces-redirect=true";
		}
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getUrlRetorno() {
		return urlRetorno;
	}
	
	public void setUrlRetorno(String urlRetorno) {
		this.urlRetorno = urlRetorno;
	}
	
	private void addMessage(String message, Severity severity) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, message, null));
	}
}
