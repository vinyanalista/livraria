package br.ufs.livraria.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.ufs.livraria.enumeration.PermissaoFuncionario;
import br.ufs.livraria.modelo.Funcionario;

@Named
@RequestScoped
public class ControleAcessoFuncionario {
	public boolean possuiPermissao(Funcionario funcionario, String uri) {
		if (uri.startsWith("/livraria/funcionario/funcionario/")) {
			return funcionario.getPermissao() == PermissaoFuncionario.ADMINISTRADOR.getValor();
		}
		return true;
	}
}
