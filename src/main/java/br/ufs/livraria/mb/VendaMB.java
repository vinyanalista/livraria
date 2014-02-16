package br.ufs.livraria.mb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufs.livraria.dao.VendaDAO;
import br.ufs.livraria.enumeration.MensagemTipo;
import br.ufs.livraria.modelo.Mensagem;
import br.ufs.livraria.modelo.Venda;

@Named
@ViewScoped
public class VendaMB implements Serializable {
	private static final long serialVersionUID = 1L;

	protected Integer id;
	
	private Venda venda;
	
	@EJB
	private VendaDAO vendaDao;
	
	@Inject
	private MensagensMB mensagensMb;
	
	public VendaMB() {
		venda = new Venda();
		id = null;
	}
	
	/* Getters e setters */
	
	public Integer getId() {
		return id;
	}
	
	public Venda getVenda() {
		return venda;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	/* Ações */
	
	public String excluir(Venda venda) {
		Mensagem mensagem;
		String proximaPagina;
		try {
			vendaDao.remover(venda);
			mensagem = new Mensagem(MensagemTipo.SUCCESSO, "O registro de venda foi excluído com sucesso!");
			proximaPagina = "index.jsf?faces-redirect=true";
		} catch (Exception excecao) {
			mensagem = new Mensagem(MensagemTipo.ERRO, "Ocorreu um erro durante o processamento da solicitação.");
			proximaPagina = "index.jsf";
		}
		mensagensMb.adicionarMensagem(mensagem);
		return proximaPagina;
	}
	
	public List<Venda> listar() {
		return vendaDao.listar();
	}
	
}