package br.ufs.livraria.modelo;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class ItemLivro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
	
	@Column(length = 4)
	private Integer quantidade;
	
	@Column(name = "preco_efetivo", precision = 8, scale = 2)
	private Float precoEfetivo;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Movimentacao movimentacao;

	// TODO Existe um jeito de excluir os itens automaticamente ao excluir o livro? 
	@ManyToOne(/* cascade = CascadeType.ALL, */fetch = FetchType.EAGER)
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

	public Float getPrecoEfetivo() {
		return precoEfetivo;
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

	public void setPrecoEfetivo(Float precoEfetivo) {
		this.precoEfetivo = precoEfetivo;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ItemLivro) {
			ItemLivro outroItemLivro = (ItemLivro) obj;
			return (this.livro.equals(outroItemLivro.livro))
					&& (this.quantidade.equals(outroItemLivro.quantidade));
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