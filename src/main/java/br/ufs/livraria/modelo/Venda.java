package br.ufs.livraria.modelo;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@DiscriminatorValue("2")
public class Venda extends Movimentacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Cliente cliente;

	@OneToOne
	private Pagamento pagamento;

	public Venda() {
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

}