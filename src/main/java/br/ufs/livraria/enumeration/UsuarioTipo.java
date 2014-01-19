package br.ufs.livraria.enumeration;

public enum UsuarioTipo {
	USUARIO(0, "Usuário"), FUNCIONARIO(1, "Funcionário"), CLIENTE(2, "Cliente");

	private final int valor;
	private final String descricao;

	private UsuarioTipo(int valor, String descricao) {
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