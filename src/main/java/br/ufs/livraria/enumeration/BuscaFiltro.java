package br.ufs.livraria.enumeration;

public enum BuscaFiltro {
	TUDO(1, "Tudo"),
	TITULO(2, "Título"),
	AUTOR(3, "Autor"),
	SINOPSE(4, "Sinopse");

	private final int value;
	private final String label;

	private BuscaFiltro(int value, String label) {
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