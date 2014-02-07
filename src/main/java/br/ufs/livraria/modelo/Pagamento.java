package br.ufs.livraria.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Pagamento extends Entidade implements Serializable {
	private static final long serialVersionUID = 1L;

	private Boolean aprovado;
	
	@Column(length = 2)
	private Integer tipo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	public Pagamento() {
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