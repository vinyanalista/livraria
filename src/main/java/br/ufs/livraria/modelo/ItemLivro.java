package br.ufs.livraria.modelo;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class ItemLivro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	private Integer quantidade;
	private Float preco;
	@ManyToOne
	@JoinColumn(nullable = false)
	private Movimentacao movimentacao;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	private Livro livro;
	
	public ItemLivro() {
	}

	public Integer getId() {
		return id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Float getPreco() {
		return preco;
	}

	public Livro getLivro() {
		return livro;
	}

	public Movimentacao getMovimentacao() {
		return movimentacao;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

}