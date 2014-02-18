package br.ufs.livraria.modelo;

import java.io.Serializable;

import javax.persistence.*;

import br.ufs.livraria.enumeration.Estado;

@Entity
public class Endereco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
	
	private String logradouro;
	
	@Column(length = 50)
	private String bairro;
	
	private Integer numero;
	
	@Column(length = 50)
	private String cidade;
	
	@Column(length = 2)
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
	@Column(length = 9)
	private String cep;
	
	public Endereco() {
		estado = Estado.SE;
	}

	public Integer getId() {
		return id;
	}
	
	public String getLogradouro() {
		return logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public Integer getNumero() {
		return numero;
	}

	public String getCidade() {
		return cidade;
	}
	
	public Estado getEstado() {
		return estado;
	}

	public String getCep() {
		return cep;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Endereco) {
			Endereco outroEndereco = (Endereco) obj;
			return (this.id == outroEndereco.id);
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