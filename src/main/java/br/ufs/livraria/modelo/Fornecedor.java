package br.ufs.livraria.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Pattern;

@Entity
public class Fornecedor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer id;

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