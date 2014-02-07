package br.ufs.livraria.modelo;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Endereco extends Entidade implements Serializable {
	private static final long serialVersionUID = 1L;

	private String logradouro;
	
	@Column(length = 50)
	private String bairro;
	
	private Integer numero;
	
	@Column(length = 50)
	private String cidade;
	
	@Column(length = 50)
	private String estado;
	
	@Column(length = 50)
	private String cep;
	
	public Endereco() {
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public Integer getNumero() {
		return numero;
	}

	public String getCidade() {
		return cidade;
	}
	
	public String getEstado() {
		return estado;
	}

	public String getCep() {
		return cep;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

}