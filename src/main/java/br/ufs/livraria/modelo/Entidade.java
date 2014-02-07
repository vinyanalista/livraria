package br.ufs.livraria.modelo;

import java.io.Serializable;

import javax.persistence.*;

@MappedSuperclass
public abstract class Entidade implements Serializable {
	private static final long serialVersionUID = 1L;
        
        @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}