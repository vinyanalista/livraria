package br.ufs.livraria.enumeration;

public enum Genero {
	ADMINISTRACAO(1, "Administração"),
	AGROPECUARIA(2, "Agropecuária"),
	ARTES(3, "Artes"),
	CIENCIAS_BIOLOGICAS(4, "Ciências biológicas"),
	CIENCIAS_EXATAS(5, "Ciências exatas"),
	CIENCIAS_HUMANAS_E_SOCIAIS(6, "Ciências humanas e sociais"),
	CONTABILIDADE(7, "Contabilidade"),
	CURSOS_E_IDIOMAS(8, "Cursos e idiomas"),
	DIDATICOS(9, "Didáticos"),
	DIREITO(10, "Direito"),
	ECONOMIA(11, "Economia"),
	INFORMATICA(12, "Informática"),
	LITERATURA_ESTRANGEIRA(13, "Literatura estrangeira"),
	LITERATURA_INFANTO_JUVENIL(14, "Literatura infanto-juvenil"),
	LITERATURA_NACIONAL(15, "Literatura nacional"),
	MEDICINA(16, "Medicina"),
	RELIGIAO(17, "Religião"),
	TURISMO(18, "Turismo");

	private final int value;
	private final String label;

	private Genero(int value, String label) {
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