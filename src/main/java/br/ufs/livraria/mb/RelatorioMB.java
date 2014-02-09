package br.ufs.livraria.mb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufs.livraria.dao.VendaDAO;
import br.ufs.livraria.enumeration.MensagemTipo;
import br.ufs.livraria.modelo.Venda;

@Named
@SessionScoped
public class RelatorioMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private Venda venda;
	
	@EJB
	private VendaDAO vendaDao;
	
	@Inject
	private MensagensMB mensagensMb;
	
	public RelatorioMB() {
		venda = new Venda();
	}
	
	/* Getters e setters */
	
	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	public List<Venda> getListaDeVendas() {
		return vendaDao.listar();
	}
	
	/* Ações */
	public String listar() {
		try {
			vendaDao.listar();
			mensagensMb.adicionarMensagem(MensagemTipo.SUCCESSO, "Busca feita com sucesso!");
			return "index.jsf?faces-redirect=true";
		} catch (Exception excecao) {
			mensagensMb.adicionarMensagem(MensagemTipo.ERRO, "Ocorreu um erro durante o processamento da solicitação.");
			return "index.jsf";
		}
	}
	
	public String excluir() {
		try {
			vendaDao.remover(venda.getId());
			mensagensMb.adicionarMensagem(MensagemTipo.SUCCESSO, "O registro de venda foi excluído com sucesso!");
			return "index.jsf?faces-redirect=true";
		} catch (Exception e) {
			mensagensMb.adicionarMensagem(MensagemTipo.ERRO, "Ocorreu um erro durante o processamento da solicitação.");
			return "index.jsf";
		}
	}
	
	/* Outros */
	
	public boolean isCadastro() {
		return (venda.getId() == null);
	}
	
}