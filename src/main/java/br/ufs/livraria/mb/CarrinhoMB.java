package br.ufs.livraria.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufs.livraria.modelo.ItemLivro;

@Named
@SessionScoped
public class CarrinhoMB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	List<ItemLivro> itens;
	
	public CarrinhoMB() {
		itens = new ArrayList<ItemLivro>();
		System.out.println("CarrinhoMB criado!");
	}
	
	public Integer getQuantidadeDeItens() {
		System.out.println("getQuantidadeDeItens()");
		return itens.size();
	}
	
	public Float getValorTotal() {
		System.out.println("getValorTotal()");
		float valorTotal = 0;
		for (ItemLivro item : itens) {
			valorTotal += (item.getPreco() * item.getQuantidade());
		}
		return valorTotal;
	}
}