package br.ufs.livraria.enumeration;

public enum Genero {
	TUDO(1, "Tudo"),
	ADMINISTRACAO(2, "Administração"),
	AGROPECUARIA(3, "Agropecuária"),
	ARTES(4, "Artes"),
	CIENCIAS_BIOLOGICAS(5, "Ciências biológicas"),
	CIENCIAS_EXATAS(6, "Ciências exatas"),
	CIENCIAS_HUMANAS_E_SOCIAIS(7, "Ciências humanas e sociais"),
	CONTABILIDADE(8, "Contabilidade"),
	CURSOS_E_IDIOMAS(9, "Cursos e idiomas"),
	DIDATICOS(10, "Didáticos"),
	DIREITO(11, "Direito"),
	ECONOMIA(12, "Economia"),
	INFORMATICA(13, "Informática"),
	LITERATURA_ESTRANGEIRA(14, "Literatura estrangeira"),
	LITERATURA_INFANTO_JUVENIL(15, "Literatura infanto-juvenil"),
	LITERATURA_NACIONAL(16, "Literatura nacional"),
	MEDICINA(17, "Medicina"),
	RELIGIAO(18, "Religião"),
	TURISMO(19, "Turismo");

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