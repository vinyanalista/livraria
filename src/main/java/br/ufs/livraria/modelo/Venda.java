package br.ufs.livraria.modelo;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@DiscriminatorValue("2")
public class Venda extends Movimentacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "venda", orphanRemoval = true)
	private Boleto boleto;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private Cliente cliente;

	public Venda() {
	}
	
	public Boleto getBoleto() {
		return boleto;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setBoleto(Boleto boleto) {
		this.boleto = boleto;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}