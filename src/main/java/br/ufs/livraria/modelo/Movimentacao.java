package br.ufs.livraria.modelo;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("0")
public abstract class Movimentacao extends Entidade implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.TIMESTAMP)
	protected Date data;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "movimentacao_id")
	protected List<ItemLivro> listaItens;
	
	public Movimentacao() {
	}

	public Date getData() {
		return data;
	}

	public List<ItemLivro> getListaItens() {
		return listaItens;
	}

	public void setData(Date data) {
		this.data = data;
	}

}