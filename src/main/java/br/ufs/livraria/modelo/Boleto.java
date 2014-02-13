package br.ufs.livraria.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Boleto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Venda venda;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	@OneToOne
	@JoinColumn(nullable = false)
	private Cliente cliente;

	public Integer getId() {
		return id;
	}

	public Venda getVenda() {
		return venda;
	}

	public Date getData() {
		return data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

}