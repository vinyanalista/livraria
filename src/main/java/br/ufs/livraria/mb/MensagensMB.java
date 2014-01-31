package br.ufs.livraria.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.ufs.livraria.enumeration.MensagemTipo;
import br.ufs.livraria.modelo.Mensagem;

@Named
@SessionScoped
public class MensagensMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<Mensagem> listaDeMensagens;
	
	public MensagensMB() {
		esvaziar();
	}
	
	public void adicionarMensagem(Mensagem mensagem) {
		listaDeMensagens.add(mensagem);
	}
	
	public void adicionarMensagem(MensagemTipo tipo, String conteudo) {
		adicionarMensagem(new Mensagem(tipo, conteudo));
	}
	
	public void esvaziar() {
		listaDeMensagens = new ArrayList<Mensagem>();
	}
	
	public List<Mensagem> getListaDeMensagens() {
		List<Mensagem> listaDeMensagens = new ArrayList<Mensagem>();
		for (FacesMessage facesMessage : FacesContext.getCurrentInstance().getMessageList()) {
			MensagemTipo tipo;
			if (facesMessage.getSeverity().getOrdinal() == FacesMessage.SEVERITY_WARN.getOrdinal()) {
				tipo = MensagemTipo.ADVERTENCIA;
			} else if (facesMessage.getSeverity().getOrdinal() == FacesMessage.SEVERITY_ERROR.getOrdinal()) {
				tipo = MensagemTipo.ERRO;
			} else if (facesMessage.getSeverity().getOrdinal() == FacesMessage.SEVERITY_FATAL.getOrdinal()) {
				tipo = MensagemTipo.ERRO;
			} else {
				tipo = MensagemTipo.INFO;
			}
			listaDeMensagens.add(new Mensagem(tipo, facesMessage.getSummary()));
		}
		listaDeMensagens.addAll(this.listaDeMensagens);
		return listaDeMensagens;
	}
}