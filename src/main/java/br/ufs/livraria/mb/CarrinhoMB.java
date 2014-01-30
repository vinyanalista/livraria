package br.ufs.livraria.mb;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufs.livraria.modelo.ItemLivro;

@Named
@SessionScoped
public class CarrinhoMB {
	List<ItemLivro> itens;
	
	public CarrinhoMB() {
		itens = new ArrayList<ItemLivro>();
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
}