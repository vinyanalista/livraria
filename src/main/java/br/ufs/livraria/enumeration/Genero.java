package br.ufs.livraria.enumeration;

public enum Genero {
	TUDO("Tudo"),
	ADMINISTRACAO("Administração"),
	AGROPECUARIA("Agropecuária"),
	ARTES("Artes"),
	CIENCIAS_BIOLOGICAS("Ciências biológicas"),
	CIENCIAS_EXATAS("Ciências exatas"),
	CIENCIAS_HUMANAS_E_SOCIAIS("Ciências humanas e sociais"),
	CONTABILIDADE("Contabilidade"),
	CURSOS_E_IDIOMAS("Cursos e idiomas"),
	DIDATICOS("Didáticos"),
	DIREITO("Direito"),
	ECONOMIA("Economia"),
	INFORMATICA("Informática"),
	LITERATURA_ESTRANGEIRA("Literatura estrangeira"),
	LITERATURA_INFANTO_JUVENIL("Literatura infanto-juvenil"),
	LITERATURA_NACIONAL("Literatura nacional"),
	MEDICINA("Medicina"),
	RELIGIAO("Religião"),
	TURISMO("Turismo");

	private final String label;

	private Genero(String label) {
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