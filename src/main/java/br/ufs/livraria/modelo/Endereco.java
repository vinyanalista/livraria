package br.ufs.livraria.modelo;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	private String logradouro;
	private String bairro;
	private Integer numero;
	private String cidade;
	private String estado;
	private String cep;
	
	public Endereco() {
	}

	public Integer getId() {
		return id;
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

	public void setId(Integer id) {
		this.id = id;
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