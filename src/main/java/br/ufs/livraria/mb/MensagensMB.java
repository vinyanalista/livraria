package br.ufs.livraria.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufs.livraria.enumeration.MensagemTipo;
import br.ufs.livraria.modelo.Mensagem;

@Named
@SessionScoped
public class MensagensMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean vazio;
	
	private List<Mensagem> listaDeMensagens;
	
	public MensagensMB() {
		esvaziar();
	}
	
	public void adicionarMensagem(Mensagem mensagem) {
		listaDeMensagens.add(mensagem);
		vazio = false;
	}
	
	public void adicionarMensagem(MensagemTipo tipo, String conteudo) {
		adicionarMensagem(new Mensagem(tipo, conteudo));
	}
	
	private void esvaziar() {
		listaDeMensagens = new ArrayList<Mensagem>();
		vazio = true;
	}
	
	public boolean isVazio() {
		return vazio;
	}
	
	public List<Mensagem> getListaDeMensagens() {
		List<Mensagem> listaDeMensagens = this.listaDeMensagens;
		esvaziar();
		return listaDeMensagens;
	}
}