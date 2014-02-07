package br.ufs.livraria.mb;

import java.io.Serializable;
import java.text.MessageFormat;
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
	
	@Inject
	private LoginInfo loginInfo;
	
	@Inject
	private MensagensMB mensagensMb;
	
	private Compra compra;
	private Map<String, Boolean> fornecedorCheckMap = new HashMap<>();
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
	
	public String salvar() {
		try {
			boolean cadastro = isCadastro();
			if (cadastro) {
				compraDAO.inserir(compra);
			} else {
				compraDAO.atualizar(compra);
			}
			for (ItemLivro itemLivro : itemLivroMap.values()) {
				itemLivroDAO.inserir(itemLivro);
				Livro livro = itemLivro.getLivro();
				livro.setEstoque(livro.getEstoque() + itemLivro.getQuantidade());
				livroDAO.atualizar(livro);
			}
			mensagensMb.adicionarMensagem(MensagemTipo.SUCCESSO,
					MessageFormat.format("A compra foi {0} com sucesso!", cadastro ? "cadastrada" : "atualizada"));
			return "index.jsf?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			mensagensMb.adicionarMensagem(MensagemTipo.ERRO,
					"Ocorreu um erro durante o processamento da solicitação.");
			return "cadastro.jsf";
		}
	}
	
	public void excluir() {
		compraDAO.remover(compra.getId());
	}
	
	public Compra getCompra() {
		return compra;
	}
	
	public void setCompra(Compra compra) {
		this.compra = compra;
		this.fornecedorCheckMap.put(compra.getFornecedor().getCnpj(), true);
		for (ItemLivro itemLivro : compra.getListaItens()) {
			this.livroCheckMap.put(itemLivro.getLivro().getId(), true);
			this.itemLivroMap.put(itemLivro.getLivro().getId(), itemLivro);
		}
	}
	
	public boolean isCadastro() {
		return this.compra.getId() == null;
	}
	
	public void setFornecedor(Fornecedor fornecedor) {
		compra.setFornecedor(fornecedor);
		for (String key : fornecedorCheckMap.keySet()) {
			if (!key.equals(fornecedor.getCnpj())) {
				fornecedorCheckMap.put(key, false);
			}
		}
	}
	
	public Map<String, Boolean> getFornecedorCheckMap() {
		return fornecedorCheckMap;
	}
	
	public Map<Integer, Boolean> getLivroCheckMap() {
		return livroCheckMap;
	}
	
	public Map<Integer, ItemLivro> getItemLivroMap() {
		return itemLivroMap;
	}
	
	public void prepareItemLivro(Livro livro) {
		if (itemLivroMap.containsKey(livro.getId())) {
			itemLivroMap.remove(livro.getId());
		} else {
			ItemLivro itemLivro = new ItemLivro();
			itemLivro.setLivro(livro);
			itemLivro.setMovimentacao(compra);
			itemLivroMap.put(livro.getId(), itemLivro);
		}
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
		setCompra(compraDAO.buscar(id));
	}
}
