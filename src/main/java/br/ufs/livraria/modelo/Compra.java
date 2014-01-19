package br.ufs.livraria.modelo;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@DiscriminatorValue("1")
public class Compra extends Movimentacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Fornecedor fornecedor;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Funcionario funcionario;
	
	public Compra() {
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

}