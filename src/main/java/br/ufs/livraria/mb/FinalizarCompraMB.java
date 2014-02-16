package br.ufs.livraria.mb;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufs.livraria.bean.LoginInfo;
import br.ufs.livraria.dao.BoletoDAO;
import br.ufs.livraria.dao.ClienteDAO;
import br.ufs.livraria.dao.ItemLivroDAO;
import br.ufs.livraria.dao.LivroDAO;
import br.ufs.livraria.dao.VendaDAO;
import br.ufs.livraria.enumeration.Estado;
import br.ufs.livraria.enumeration.MensagemTipo;
import br.ufs.livraria.modelo.Boleto;
import br.ufs.livraria.modelo.Cliente;
import br.ufs.livraria.modelo.ItemLivro;
import br.ufs.livraria.modelo.Livro;
import br.ufs.livraria.modelo.Venda;

@Named
@SessionScoped
public class FinalizarCompraMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private CarrinhoMB carrinhoMB;
	
	@EJB
	private BoletoDAO boletoDao;
	
	@EJB
	private ClienteDAO clienteDao;
	
	@EJB
	private ItemLivroDAO itemLivroDao;
	
	@EJB
	private LivroDAO livroDao;
	
	@Inject
	private LoginInfo loginInfo;

	@Inject
	private MensagensMB mensagensMb;
	
	private boolean revisarItens;
	
	@EJB
	private VendaDAO vendaDao;
	
	public String adicionar(Livro livro) {
		carrinhoMB.adicionar(livro);
		verificarDisponibilidadeDosLivros();
		return "finalizar_compra.jsf";
	}
	
	private Livro atualizarLivro(Livro livro) {
		return livroDao.buscar(livro.getId());
	}

	public String finalizarCompra() {
		if (!haItens()) {
			mensagensMb.adicionarMensagem(MensagemTipo.ERRO, "Adicione pelo menos um livro ao carrinho!");
			return "carrinho.jsf?faces-redirect=true";
		}
		if (!loginInfo.isLoggedIn()) {
			return "finalizar_compra_login.jsf?faces-redirect=true";
		} else {
			return "finalizar_compra.jsf?faces-redirect=true";
		}
	}
	
	public String gerarBoleto() {
		if (!haItens()) {
			mensagensMb.adicionarMensagem(MensagemTipo.ERRO, "Requisição inválida");
			return "carrinho.jsf?faces-redirect=true";
		}
		verificarDisponibilidadeDosLivros();
		if (revisarItens) {
			return "finalizar_compra.jsf";
		} else {
			clienteDao.atualizar(getCliente());
			Venda venda = new Venda();
			venda.setCliente(getCliente());
			vendaDao.inserir(venda);
			for (ItemLivro item : carrinhoMB.getItens()) {
				Livro livro = item.getLivro();
				Integer estoqueAntigo = livro.getEstoque();
				Integer exemplaresDaVenda = item.getQuantidade();
				livro.setEstoque(estoqueAntigo - exemplaresDaVenda);
				livroDao.atualizar(livro);
				item.setMovimentacao(venda);
				venda.getItens().add(item);
				itemLivroDao.inserir(item);
			}
			vendaDao.atualizar(venda);
			Boleto boleto = new Boleto();
			boleto.setVenda(venda);
			boletoDao.inserir(boleto);
			carrinhoMB.esvaziar();
			return "boleto.jsf?faces-redirect=true&id=" + boleto.getId(); // TODO Implementar impressão de boleto
		}
	}
	
	public Cliente getCliente() {
		return (Cliente) loginInfo.getUsuarioLogado();
	}
	
	public Estado[] getEstados() {
		return Estado.values();
	}
	
	public List<ItemLivro> getItens() {
		return carrinhoMB.getItens();
	}
	
	public Float getValorTotal() {
		return carrinhoMB.getValorTotal();
	}
	
	public boolean haItens() {
		return (getItens().size() > 0);
	}
	
	public boolean isRevisarItens() {
		return revisarItens;
	}
	
	public String remover(Livro livro) {
		carrinhoMB.remover(livro);
		if (!haItens()) {
			return "carrinho.jsf?faces-redirect=true";
		}
		verificarDisponibilidadeDosLivros();
		return "finalizar_compra.jsf";
	}
	
	public String removerTodos(Livro livro) {
		carrinhoMB.removerTodos(livro);
		if (!haItens()) {
			return "carrinho.jsf?faces-redirect=true";
		}
		verificarDisponibilidadeDosLivros();
		return "finalizar_compra.jsf";
	}
	
	public void verificarDisponibilidadeDosLivros() {
		if (!haItens()) {
			mensagensMb.adicionarMensagem(MensagemTipo.ERRO, "Requisição inválida");
			try {
				FacesContext.getCurrentInstance().getExternalContext().redirect("carrinho.jsf");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		revisarItens = false;
		for (ItemLivro item : new ArrayList<ItemLivro>(carrinhoMB.getItens())) {
			item.setLivro(atualizarLivro(item.getLivro()));
			if (item.getLivro().getEstoque() < item.getQuantidade()) {
				revisarItens = true;
				break;
			}
		}
	}

}