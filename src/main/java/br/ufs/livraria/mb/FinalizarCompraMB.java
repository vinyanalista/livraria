package br.ufs.livraria.mb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufs.livraria.bean.LoginInfo;
import br.ufs.livraria.dao.LivroDAO;
import br.ufs.livraria.enumeration.Estado;
import br.ufs.livraria.modelo.Cliente;
import br.ufs.livraria.modelo.ItemLivro;
import br.ufs.livraria.modelo.Livro;

@Named
@RequestScoped
public class FinalizarCompraMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private CarrinhoMB carrinhoMB;
	
	private Cliente cliente;
	
	@EJB
	private LivroDAO livroDao;
	
	@Inject
	private LoginInfo loginInfo;

	@Inject
	private MensagensMB mensagensMb;
	
	private boolean revisarItens;
	
	public FinalizarCompraMB() {
		cliente = new Cliente(); // TODO Teste
	}
	
	public String adicionar(Livro livro) {
		carrinhoMB.adicionar(livro);
		return "finalizar_compra.jsf";
	}
	
	private Livro atualizarLivro(Livro livro) {
		return livroDao.buscar(livro.getId());
	}

	public String finalizarCompra() {
		if (!loginInfo.isLoggedIn()) {
			return "finalizar_compra_login.jsf?faces-redirect=true";
		} else {
			return "finalizar_compra.jsf?faces-redirect=true";
		}
	}
	
	public String gerarBoleto() {
		return "boleto.jsf?faces-redirect=true"; // TODO Implementar geração de boleto
	}
	
	public Cliente getCliente() {
		return cliente;
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
	
	public boolean isRevisarItens() {
		return revisarItens;
	}
	
	public String remover(Livro livro) {
		carrinhoMB.remover(livro);
		return "finalizar_compra.jsf";
	}
	
	public String removerTodos(Livro livro) {
		carrinhoMB.removerTodos(livro);
		return "finalizar_compra.jsf";
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public void verificarDisponibilidadeDosLivros() {
		revisarItens = false;
		for (ItemLivro item : carrinhoMB.getItens()) {
			item.setLivro(atualizarLivro(item.getLivro()));
			if (item.getLivro().getEstoque() < item.getQuantidade()) {
				revisarItens = true;
				break;
			}
		}
	}

}