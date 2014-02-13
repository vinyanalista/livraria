package br.ufs.livraria.modelo;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.INTEGER)
public abstract class Movimentacao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
	
	@Temporal(TemporalType.TIMESTAMP)
	protected Date data;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE, mappedBy = "movimentacao")
	@JoinColumn(name = "movimentacao_id")
	protected List<ItemLivro> listaItens;
	
	public Movimentacao() {
	}
	
	public Integer getId() {
		return id;
	}

	public Date getData() {
		return data;
	}

	public List<ItemLivro> getListaItens() {
		return listaItens;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Movimentacao) {
			Movimentacao outraMovimentacao = (Movimentacao) obj;
			return this.id == outraMovimentacao.id;
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

}