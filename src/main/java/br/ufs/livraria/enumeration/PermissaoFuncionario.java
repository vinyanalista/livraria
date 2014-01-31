
package br.ufs.livraria.enumeration;

public enum PermissaoFuncionario {
	ADMINISTRADOR(0, "0"), FUNCIONARIO(1, "1");
	
	private final int valor;
	private final String classe;

	private PermissaoFuncionario(int valor, String classe) {
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