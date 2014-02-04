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
		itens = new ArrayList<ItemLivro>();
	}
	
	public void inicializar() {
		// TODO Apenas para fins de protótipo! Remover!
		if (itens.size() == 0) {
			Livro livro1 = livroDao.buscar(1);
			ItemLivro item1 = new ItemLivro();
			item1.setLivro(livro1);
			item1.setPreco(livro1.getPreco());
			item1.setQuantidade(1);
			itens.add(item1);
			
			Livro livro2 = livroDao.buscar(2);
			ItemLivro item2 = new ItemLivro();
			item2.setLivro(livro2);
			item2.setPreco(livro2.getPreco());
			item2.setQuantidade(2);
			itens.add(item2);
			
			Livro livro3 = livroDao.buscar(3);
			ItemLivro item3 = new ItemLivro();
			item3.setLivro(livro3);
			item3.setPreco(livro3.getPreco());
			item3.setQuantidade(3);
			itens.add(item3);
		}
	}
	
	public String adicionar(Livro livro) {
		boolean livroJaExistia = false;
		for (ItemLivro item : itens) {
			if (item.getLivro().equals(livro)) {
				item.setQuantidade(item.getQuantidade() + 1);
				livroJaExistia = true;
				mensagensMb.adicionarMensagem(MensagemTipo.SUCCESSO, "Mais um exemplar do livro <b>" + livro.getTitulo() + "</b> foi adicionado ao carrinho!");
			}
		}
		if (!livroJaExistia) {
			ItemLivro novoItem = new ItemLivro();
			novoItem.setLivro(livro);
			novoItem.setQuantidade(1);
			itens.add(novoItem);
			// TODO As tags <b></b> estão sendo impressas
			mensagensMb.adicionarMensagem(MensagemTipo.SUCCESSO, "O livro <b>" + livro.getTitulo() + "</b> foi adicionado ao carrinho!");
		}
		return "carrinho.jsf?faces-redirect=true";
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
			valorTotal += (item.getPreco() * item.getQuantidade());
		}
		return valorTotal;
	}
	
	public String remover(Livro livro) {
		for (ItemLivro item : itens) {
			if (item.getLivro().equals(livro)) {
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
				itens.remove(item);
				mensagensMb.adicionarMensagem(MensagemTipo.SUCCESSO, "O livro <b>" + livro.getTitulo() + "</b> foi removido do carrinho!");
				break;
			}
		}
		return "carrinho.jsf?faces-redirect=true";
	}
}