package br.ufs.livraria.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
// import javax.validation.constraints.Pattern;

@Entity
public class Fornecedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
	
	@Column(unique = true, length = 18)
	// @Pattern(regexp = "[0-9]*")
	private String cnpj;

	@Column(length = 15)
	// @Pattern(regexp = "[0-9]*")
	private String telefone;

	@Column(length = 50)
	private String email;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(nullable = false)
	private Endereco endereco;

	@Column(length = 50, nullable = false)
	private String nome;

	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "fornecedor")
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
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Fornecedor) {
			Fornecedor outroFornecedor = (Fornecedor) obj;
			return (this.id == outroFornecedor.id);
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