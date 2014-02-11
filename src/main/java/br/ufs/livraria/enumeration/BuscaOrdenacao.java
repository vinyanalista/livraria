package br.ufs.livraria.enumeration;

public enum BuscaOrdenacao {
	TITULO_A_Z(1, "Título de A a Z"),
	TITULO_Z_A(2, "Título de Z a A"),
	ESTOQUE(3, "Estoque"),
	MENOR_PRECO(4, "Menor preço");

	private final int value;
	private final String label;

	private BuscaOrdenacao(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}
	
	public String getLabel() {
		return label;
	}

	@Override
	public String toString() {
		return label;
	}

}