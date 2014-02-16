package br.ufs.livraria.enumeration;

public enum StatusPagamento {
	CONFIRMADO("Confirmado"), 	  // 0
	EM_ANDAMENTO("Em andamento"), // 1
	CANCELADO("Cancelado");		  // 2

	private final String label;

	private StatusPagamento(String label) {
		this.label = label;
	}

	public int getValue() {
		return this.ordinal();
	}
	
	public String getLabel() {
		return label;
	}

	@Override
	public String toString() {
		return label;
	}
}
