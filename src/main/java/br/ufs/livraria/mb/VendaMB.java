package br.ufs.livraria.mb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufs.livraria.dao.LivroDAO;
import br.ufs.livraria.dao.VendaDAO;
import br.ufs.livraria.enumeration.MensagemTipo;
import br.ufs.livraria.enumeration.StatusPagamento;
import br.ufs.livraria.modelo.ItemLivro;
import br.ufs.livraria.modelo.Livro;
import br.ufs.livraria.modelo.Mensagem;
import br.ufs.livraria.modelo.Venda;

@Named
@ViewScoped
public class VendaMB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Boolean filtrarPendentes;
	
	private Integer id;
	
	private Venda venda;
	
	@EJB
	private LivroDAO livroDao;
	
	@EJB
	private VendaDAO vendaDao;
	
	@Inject
	private MensagensMB mensagensMb;
	
	public VendaMB() {
		filtrarPendentes = false;
		id = null;
		venda = new Venda();
	}
	
	/* Getters e setters */
	
	public Boolean getFiltrarPendentes() {
		return filtrarPendentes;
	}
	
	public Integer getId() {
		return id;
	}
	
	public Venda getVenda() {
		return venda;
	}

	public void setFiltrarPendentes(Boolean filtrarPendentes) {
		this.filtrarPendentes = filtrarPendentes;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	/* Ações */
	
	public String cancelar(Venda venda) {
		Mensagem mensagem;
		String proximaPagina;
		try {
			venda.getBoleto().setStatusPagamento(StatusPagamento.CANCELADO);
			vendaDao.atualizar(venda);
			for (ItemLivro item : venda.getItens()) {
				Livro livro = item.getLivro();
				Integer estoqueAntigo = livro.getEstoque();
				Integer exemplaresDaVenda = item.getQuantidade();
				livro.setEstoque(estoqueAntigo + exemplaresDaVenda);
				livroDao.atualizar(livro);
			}
			mensagem = new Mensagem(MensagemTipo.SUCCESSO,
					"A venda foi cancelada com sucesso!");
			proximaPagina = "index.jsf?faces-redirect=true&filtrarPendentes=true";
		} catch (Exception excecao) {
			mensagem = new Mensagem(MensagemTipo.ERRO,
					"Ocorreu um erro ao tentar cancelar a venda.");
			proximaPagina = "index.jsf?filtrarPendentes=true";
		}
		mensagensMb.adicionarMensagem(mensagem);
		return proximaPagina;
	}
	
	public String confirmar(Venda venda) {
		Mensagem mensagem;
		String proximaPagina;
		try {
			venda.getBoleto().setStatusPagamento(StatusPagamento.CONFIRMADO);
			vendaDao.atualizar(venda);
			mensagem = new Mensagem(MensagemTipo.SUCCESSO,
					"O pagamento do boleto foi confirmado com sucesso!");
			proximaPagina = "index.jsf?faces-redirect=true&filtrarPendentes=true";
		} catch (Exception excecao) {
			mensagem = new Mensagem(MensagemTipo.ERRO,
					"Ocorreu um erro ao tentar confirmar o pagamento do boleto.");
			proximaPagina = "index.jsf?filtrarPendentes=true";
		}
		mensagensMb.adicionarMensagem(mensagem);
		return proximaPagina;
	}
	
	public void filtrar() {
		if (filtrarPendentes == null) {
			filtrarPendentes = false;
		}
	}
	
	public List<Venda> listar() {
		if (filtrarPendentes) {
			return listarPendentes();
		} else {
			return vendaDao.listar();
		}
	}
	
	public List<Venda> listarPendentes() {
		return vendaDao.pendentes();
	}
	
}