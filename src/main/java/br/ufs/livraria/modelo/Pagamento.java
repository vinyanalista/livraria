package br.ufs.livraria.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	private Boolean aprovado;
	
	@Column(length = 2)
	private Integer tipo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	public Pagamento() {
	}

	public Integer getId() {
		return id;
	}

	public Boolean getAprovado() {
		return aprovado;
	}

	public Integer getTipo() {
		return tipo;
	}

	public Date getData() {
		return data;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAprovado(Boolean aprovado) {
		this.aprovado = aprovado;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public void setData(Date data) {
		this.data = data;
	}
}