package br.ufs.livraria.mb;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufs.livraria.bean.LoginInfo;
import br.ufs.livraria.dao.CompraDAO;
import br.ufs.livraria.dao.FornecedorDAO;
import br.ufs.livraria.dao.ItemLivroDAO;
import br.ufs.livraria.dao.LivroDAO;
import br.ufs.livraria.enumeration.MensagemTipo;
import br.ufs.livraria.modelo.Compra;
import br.ufs.livraria.modelo.Fornecedor;
import br.ufs.livraria.modelo.Funcionario;
import br.ufs.livraria.modelo.ItemLivro;
import br.ufs.livraria.modelo.Livro;

@Named
@ViewScoped
public class CompraMB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private CompraDAO compraDAO;
	
	@EJB
	private ItemLivroDAO itemLivroDAO;
	
	@EJB
	private LivroDAO livroDAO;
	
	@EJB
	private FornecedorDAO fornecedorDAO;
	
	@Inject
	private LoginInfo loginInfo;
	
	@Inject
	private MensagensMB mensagensMb;
	
	private Compra compra;
	private Map<Integer, Boolean> livroCheckMap = new HashMap<>();
	private Map<Integer, ItemLivro> itemLivroMap = new HashMap<>();
	private Integer id;
	
	
	@PostConstruct
	public void init() {
		this.compra = new Compra();
		this.compra.setFuncionario((Funcionario) loginInfo.getUsuarioLogado());
	}
	
	public List<Compra> getCompras() {
		return compraDAO.listar();
	}
	
	public List<Fornecedor> getFornecedores() {
		return fornecedorDAO.listar();
	}
	
	public List<Livro> getLivros() {
		List<Livro> livros = livroDAO.listar();
		for (Livro livro : livros) {
			ItemLivro itemLivro = new ItemLivro();
			itemLivro.setLivro(livro);
			itemLivro.setMovimentacao(compra);
			itemLivroMap.put(livro.getId(), itemLivro);
		}
		return livros;
	}
	
	public String salvar() {
		try {
			if (!validar()) {
				return null;
			}
			compra.setData(new Date());
			compraDAO.inserir(compra);
			for (ItemLivro itemLivro : itemLivroMap.values()) {
				if (livroCheckMap.get(itemLivro.getLivro().getId())) {
					itemLivroDAO.inserir(itemLivro);
					Livro livro = itemLivro.getLivro();
					livro.setEstoque(livro.getEstoque() + itemLivro.getQuantidade());
					livroDAO.atualizar(livro);
				}
			}
			mensagensMb.adicionarMensagem(MensagemTipo.SUCCESSO, "A compra foi cadastrada com sucesso!");
			return "index.jsf?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			mensagensMb.adicionarMensagem(MensagemTipo.ERRO,
					"Ocorreu um erro durante o processamento da solicitação.");
			return "cadastro.jsf";
		}
	}
	
	public Compra getCompra() {
		return compra;
	}
	
	public void setCompra(Compra compra) {
		this.compra = compra;
	}
	
	public boolean isCadastro() {
		return this.compra.getId() == null;
	}
	
	public Map<Integer, Boolean> getLivroCheckMap() {
		return livroCheckMap;
	}
	
	public Map<Integer, ItemLivro> getItemLivroMap() {
		return itemLivroMap;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
		setCompra(compraDAO.buscar(id));
	}
	
	private boolean validar() {
		boolean valid = true;
		boolean possuiLivroMarcado = false;
		for (Integer idLivro : livroCheckMap.keySet()) {
			if (livroCheckMap.get(idLivro)) {
				possuiLivroMarcado = true;
				ItemLivro itemLivro = itemLivroMap.get(idLivro);
				if (itemLivro.getPrecoEfetivo() == null) {
					mensagensMb.adicionarMensagem(MensagemTipo.ERRO, MessageFormat.format("O livro {0} não possui preço", itemLivro.getLivro().getTitulo()));
					valid = false;
				}
				if (itemLivro.getQuantidade() == null) {
					mensagensMb.adicionarMensagem(MensagemTipo.ERRO, MessageFormat.format("O livro {0} não possui quantidade", itemLivro.getLivro().getTitulo()));
					valid = false;
				}
			}
		}
		if (!possuiLivroMarcado) {
			mensagensMb.adicionarMensagem(MensagemTipo.ERRO, "Você deve escolher pelo menos um livro");
		}
		return valid && possuiLivroMarcado;
	}
}
