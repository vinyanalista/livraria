package br.ufs.livraria.modelo;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class Livro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	private String titulo;
	private String sinopse;
	private Integer edicao;
	private Integer ano;
	private String isbn;
	private Integer estoque;
	private String genero;
	private String autor;
	private String editora;
	private Float preco;

	public Livro() {
	}
	
	public Integer getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public String getSinopse() {
		return sinopse;
	}

	public Integer getEdicao() {
		return edicao;
	}

	public Integer getAno() {
		return ano;
	}

	public String getIsbn() {
		return isbn;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public String getGenero() {
		return genero;
	}

	public String getAutor() {
		return autor;
	}

	public String getEditora() {
		return editora;
	}
	
	public Float getPreco() {
		return preco;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public void setEdicao(Integer edicao) {
		this.edicao = edicao;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}
	
	public void setPreco(Float preco) {
		this.preco = preco;
	}

}