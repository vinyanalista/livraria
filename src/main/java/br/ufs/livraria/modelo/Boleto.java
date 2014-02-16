package br.ufs.livraria.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import br.ufs.livraria.enumeration.StatusPagamento;

@Entity
public class Boleto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private Venda venda;
	
	@Column(name = "data_pagamento")
	@Temporal(TemporalType.DATE)
	private Date dataPagamento;
	
	@Column(name = "status_pagamento")
	@Enumerated(EnumType.ORDINAL)
	private StatusPagamento statusPagamento;
	
	public Boleto() {
		dataPagamento = new Date();
	}

	public Integer getId() {
		return id;
	}

	public Venda getVenda() {
		return venda;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}
	
	public StatusPagamento getStatusPagamento() {
		return statusPagamento;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	public void setStatusPagamento(StatusPagamento statusPagamento) {
		this.statusPagamento = statusPagamento;
	}

}