package br.ufs.livraria.mb;

import java.io.IOException;
import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufs.livraria.dao.BoletoDAO;
import br.ufs.livraria.enumeration.MensagemTipo;
import br.ufs.livraria.modelo.Boleto;

@Named
@RequestScoped
public class BoletoMB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Boleto boleto;
	
	protected Integer id;
	
	@EJB
	private BoletoDAO boletoDao;
	
	@Inject
	protected MensagensMB mensagensMb;
	
	public BoletoMB() {
		boleto = new Boleto();
		id = null;
	}
	
	/* Getters e setters */

	public Boleto getBoleto() {
		return boleto;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	/* Ações */

	public void carregar() {
		if (id == null) {
			mensagensMb.adicionarMensagem(MensagemTipo.ERRO, "Requisição inválida");
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("index.jsf");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			boleto = boletoDao.buscar(id);
		}
	}
	
}