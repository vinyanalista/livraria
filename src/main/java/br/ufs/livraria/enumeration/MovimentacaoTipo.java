package br.ufs.livraria.enumeration;

public enum MovimentacaoTipo {
	MOVIMENTACAO(0, "Movimentação"), COMPRA(1, "Compra"), VENDA(2, "Venda");

	private final int valor;
	private final String descricao;

	private MovimentacaoTipo(int valor, String descricao) {
		this.valor = valor;
		this.descricao = descricao;
	}

	public int getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return descricao;
	}

}