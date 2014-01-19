package br.ufs.livraria.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue("1")
public class Funcionario extends Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer permissao;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "funcionario", cascade = { CascadeType.REMOVE })
	private List<Compra> compras;
	
	public Funcionario() {
	}

	public Integer getPermissao() {
		return permissao;
	}

	public void setPermissao(Integer permissao) {
		this.permissao = permissao;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

}