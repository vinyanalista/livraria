package br.ufs.livraria.mbean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

import br.ufs.livraria.dao.FuncionarioDAO;
import br.ufs.livraria.modelo.Funcionario;

@ViewScoped
public class FuncionarioMBean {
	@ManagedProperty("#{FuncionarioDAO}")
	private FuncionarioDAO dao = new FuncionarioDAO();
	
	public int idFuncionarioSelected;
	


	public List<Funcionario> listAll(){
		return dao.listar();
	}
	
	public void alterar(){
		
		
	}
	
	public void remover(){
		dao.remover(idFuncionarioSelected);
	}	

	public int getIdFuncionarioSelected() {
		return idFuncionarioSelected;
	}

	public void setIdFuncionarioSelected(int idFuncionarioSelected) {
		this.idFuncionarioSelected = idFuncionarioSelected;
	}

}
