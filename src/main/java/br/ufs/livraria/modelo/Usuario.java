package br.ufs.livraria.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("0")
public abstract class Usuario extends Entidade implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(length = 50)
	protected String nome;
	
	@Column(length = 11)
	@Pattern(regexp = "[0-9]*")
	protected String cpf;
	
	@Column(length = 20)
	protected String rg;
	
	@Column(name = "data_nascimento")
	@Temporal(TemporalType.DATE)
	protected Date dataNascimento;
	
	@Column(length = 15)
	@Pattern(regexp = "[0-9]*")
	protected String telefone;
	
	@Column(length = 50)
	protected String email;

	@OneToOne
	@JoinColumn(nullable = false)
	protected Endereco endereco;

	@Column(length = 40)
	private String senha;

	public Usuario() {
	}

	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getRg() {
		return rg;
	}

	public Date getDataNascimento() {
		return dataNascimento;
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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
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

	public String getSenha() {
		return this.senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}

}