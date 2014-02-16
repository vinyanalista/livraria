package br.ufs.livraria.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
@DiscriminatorValue("1")
public class Funcionario extends Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer permissao;
	
	@Column(precision = 8, scale = 2)
	private Float salario;
	
	@Column(length = 50)
	private String cargo;
	
	@Column(name = "data_contratacao")
	@Temporal(TemporalType.DATE)
	private Date dataContratacao;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "funcionario")
	private List<Compra> compras;
	
	public Funcionario() {
	}

	public Integer getPermissao() {
		return permissao;
	}
	
	public Float getSalario() {
		return salario;
	}

	public String getCargo() {
		return cargo;
	}

	public Date getDataContratacao() {
		return dataContratacao;
	}

	public void setPermissao(Integer permissao) {
		this.permissao = permissao;
	}
	
	public void setSalario(Float salario) {
		this.salario = salario;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public void setDataContratacao(Date dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

	public List<Compra> getCompras() {
		return compras;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

}