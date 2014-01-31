package br.ufs.livraria.enumeration;

public enum MensagemTipo {
	SUCCESSO(0, "alert-success"), INFO(1, "alert-info"), ADVERTENCIA(2, "alert-warning"), ERRO(3, "alert-danger");
	
	private final int valor;
	private final String classe;

	private MensagemTipo(int valor, String classe) {
		this.valor = valor;
		this.classe = classe;
	}

	public int getValor() {
		return valor;
	}

	public String getClasse() {
		return classe;
	}

	@Override
	public String toString() {
		return classe;
	}
}