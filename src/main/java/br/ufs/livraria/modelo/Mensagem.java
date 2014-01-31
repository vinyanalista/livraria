package br.ufs.livraria.modelo;

import java.io.Serializable;

import br.ufs.livraria.enumeration.MensagemTipo;

public class Mensagem implements Serializable {
	private static final long serialVersionUID = 1L;

	private MensagemTipo tipo;
	private String conteudo;

	public Mensagem() {
		this(MensagemTipo.INFO, "");
	}

	public Mensagem(MensagemTipo tipo, String conteudo) {
		this.tipo = tipo;
		this.conteudo = conteudo;
	}

	public MensagemTipo getTipo() {
		return tipo;
	}

	public void setTipo(MensagemTipo tipo) {
		this.tipo = tipo;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
}