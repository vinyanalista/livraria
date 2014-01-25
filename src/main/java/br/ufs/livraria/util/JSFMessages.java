package br.ufs.livraria.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

public class JSFMessages {

	public static void exibirMensagem(Severity severidade, String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(severidade, mensagem, null));
	}

	public static void exibirMensagemAposRedirecionar(Severity severidade,
			String mensagem) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext()
				.getFlash();
		flash.setKeepMessages(true);
		flash.setRedirect(true);
		exibirMensagem(severidade, mensagem);
	}
}