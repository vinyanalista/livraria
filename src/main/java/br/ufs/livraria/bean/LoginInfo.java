package br.ufs.livraria.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufs.livraria.modelo.Usuario;

@Named
@SessionScoped
public class LoginInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuarioLogado;
	
	public Usuario getUsuarioLogado() {
		return this.usuarioLogado;
	}
	
	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	public boolean isLoggedIn() {
		return this.usuarioLogado != null;
	}
}
