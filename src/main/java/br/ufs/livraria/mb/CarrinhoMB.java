package br.ufs.livraria.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufs.livraria.dao.LivroDAO;
import br.ufs.livraria.enumeration.MensagemTipo;
import br.ufs.livraria.modelo.ItemLivro;
import br.ufs.livraria.modelo.Livro;

@Named
@SessionScoped
public class CarrinhoMB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<ItemLivro> itens;

	@EJB
	private LivroDAO livroDao;
	
	@Inject
	private MensagensMB mensagensMb;
	
	public CarrinhoMB() {
		esvaziar();
	}
	
	public String adicionar(Livro livro) {
		if (livro != null) {
			boolean livroJaExistia = false;
			for (ItemLivro item : itens) {
				if (item.getLivro().equals(livro)) {
					item.setQuantidade(item.getQuantidade() + 1);
					livroJaExistia = true;
					mensagensMb.adicionarMensagem(MensagemTipo.SUCCESSO, "Mais um exemplar do livro <b>" + livro.getTitulo() + "</b> foi adicionado ao carrinho!");
				}
			}
			if (!livroJaExistia) {
				livro = atualizarLivro(livro);
				if (livro.getEstoque() == 0) {
					mensagensMb.adicionarMensagem(MensagemTipo.ADVERTENCIA, "O livro <b>" + livro.getTitulo() + "</b> não foi adicionado ao carrinho por terem se esgotado as unidades disponíveis no estoque.");
				} else {
					ItemLivro novoItem = new ItemLivro();
					novoItem.setLivro(livro);
					novoItem.setPrecoEfetivo(livro.getPreco());
					novoItem.setQuantidade(1);
					itens.add(novoItem);
					mensagensMb.adicionarMensagem(MensagemTipo.SUCCESSO, "O livro <b>" + livro.getTitulo() + "</b> foi adicionado ao carrinho!");
				}
			}
		}
		return "carrinho.jsf?faces-redirect=true";
	}
	
	private Livro atualizarLivro(Livro livro) {
		return livroDao.buscar(livro.getId());
	}
	
	public void esvaziar() {
		itens = new ArrayList<ItemLivro>();
	}
	
	public List<ItemLivro> getItens() {
		return itens;
	}

	public Integer getQuantidadeDeItens() {
		return itens.size();
	}
	
	public Float getValorTotal() {
		float valorTotal = 0;
		for (ItemLivro item : itens) {
			valorTotal += item.getValorTotal();
		}
		return valorTotal;
	}
	
	public String remover(Livro livro) {
		for (ItemLivro item : itens) {
			if (item.getLivro().equals(livro)) {
				if (item.getQuantidade() == 1) {
					return removerTodos(item);
				}
				item.setQuantidade(item.getQuantidade() - 1);
				mensagensMb.adicionarMensagem(MensagemTipo.SUCCESSO, "Um exemplar do livro <b>" + livro.getTitulo() + "</b> foi removido do carrinho!");
				break;
			}
		}
		return "carrinho.jsf?faces-redirect=true";
	}
	
	public String removerTodos(Livro livro) {
		for (ItemLivro item : itens) {
			if (item.getLivro().equals(livro)) {
				return removerTodos(item);
			}
		}
		return "carrinho.jsf?faces-redirect=true";
	}

	private String removerTodos(ItemLivro item) {
		itens.remove(item);
		mensagensMb.adicionarMensagem(MensagemTipo.SUCCESSO, "O livro <b>" + item.getLivro().getTitulo() + "</b> foi removido do carrinho!");
		return "carrinho.jsf?faces-redirect=true";
	}
	
	public void verificarDisponibilidadeDosLivros() {
		for (ItemLivro item : new ArrayList<ItemLivro>(itens)) {
			item.setLivro(atualizarLivro(item.getLivro()));
			if (item.getLivro().getEstoque() == 0) {
				itens.remove(item);
				mensagensMb.adicionarMensagem(MensagemTipo.ADVERTENCIA, "O livro <b>" + item.getLivro().getTitulo() + "</b> foi removido do carrinho por terem se esgotado as unidades disponíveis no estoque.");
			}
		}
	}
}