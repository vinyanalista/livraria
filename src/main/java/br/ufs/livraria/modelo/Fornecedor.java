package br.ufs.livraria.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
public class Fornecedor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;

	@Column(unique = true)
	private String cnpj;

	private String telefone;

	private String email;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "endereco_id", nullable = false)
	private Endereco endereco;

	@Column(nullable = false)
	private String nome;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fornecedor", cascade = { CascadeType.REMOVE })
	private List<Compra> compras;
	
	public Fornecedor() {
		endereco = new Endereco();
	}

	public Integer getId() {
		return id;
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

	public void setId(Integer id) {
		this.id = id;
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