package br.ufs.livraria.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
public class Fornecedor extends Entidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(unique = true, length = 14)
//	@Pattern(regexp = "[0-9]*")
	private String cnpj;

	@Column(length = 15)
//	@Pattern(regexp = "[0-9]*")
	private String telefone;

	@Column(length = 50)
	private String email;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "endereco_id", nullable = false)
	private Endereco endereco;

	@Column(length = 50, nullable = false)
	private String nome;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fornecedor", cascade = { CascadeType.REMOVE })
	private List<Compra> compras;
	
	public Fornecedor() {
		endereco = new Endereco();
	}
	
	public String getCnpj() {
		return cnpj;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEmail() {
		return email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public String getNome() {
		return nome;
	}

	public List<Compra> getCompras() {
		return compras;
	}
	
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCompras(List<Compra> compras) {
		this.compras = compras;
	}

}