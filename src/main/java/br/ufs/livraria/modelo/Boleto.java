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
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(nullable = false)
	private Venda venda;
	
	@Temporal(TemporalType.DATE)
	private Date data;
	
	public Boleto() {
		data = new Date();
	}

	public Integer getId() {
		return id;
	}

	public Venda getVenda() {
		return venda;
	}

	public Date getData() {
		return data;
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

}