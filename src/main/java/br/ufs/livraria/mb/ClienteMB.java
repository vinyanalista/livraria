package br.ufs.livraria.mb;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.codec.digest.DigestUtils;

import br.ufs.livraria.dao.ClienteDAO;
import br.ufs.livraria.enumeration.Estado;
import br.ufs.livraria.enumeration.MensagemTipo;
import br.ufs.livraria.modelo.Cliente;
import br.ufs.livraria.modelo.Mensagem;

@Named
@RequestScoped
public class ClienteMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private Cliente cliente;

	@EJB
	private ClienteDAO clienteDao;

	private Integer id;

	@Inject
	private MensagensMB mensagensMb;

	private String senha1;
	private String senha2;
	
	private boolean compra = false;


	public ClienteMB() {
		cliente = new Cliente();
		id = null;
	}

	public List<Cliente> getListaDeClientes() {
		return clienteDao.listar();
	}

	/* Ações */

	public String atualizar() {
		try {
			if (senhasIguais()) {
				cliente.setSenha(DigestUtils.sha1Hex(senha1));
				clienteDao.atualizar(cliente);
				mensagensMb.adicionarMensagem(MensagemTipo.SUCCESSO,
						"O Cliente foi atualizado com sucesso!");
				return "index.jsf?faces-redirect=true";
			} else {
				mensagensMb.adicionarMensagem(MensagemTipo.ERRO,
						"A senha não confere");
				return null;
			}
		} catch (Exception excecao) {
			mensagensMb.adicionarMensagem(MensagemTipo.ERRO,
					"Ocorreu um erro durante o processamento da solicitação.");
			return "cadastro.jsf";
		}
	}
	
	public String excluir(Cliente cliente){
		Mensagem mensagem;
		String proximaPagina;
		try {
			clienteDao.remover(cliente);
			mensagem = new Mensagem(MensagemTipo.SUCCESSO,
					"O cliente foi excluído com sucesso!");
			proximaPagina = "index.jsf?faces-redirect=true";
		} catch (Exception excecao) {
			mensagem = new Mensagem(MensagemTipo.ERRO,
					"Ocorreu um erro ao tentar excluir o cliente.");
			proximaPagina = "index.jsf";
		}
		mensagensMb.adicionarMensagem(mensagem);
		return proximaPagina;
	}

	public String inserir(boolean funcionario) {
		try {
			if (senhasIguais()) {
				cliente.setSenha(DigestUtils.sha1Hex(senha1));
				cliente.setDataCadastro(new Date());
				clienteDao.inserir(cliente);
				mensagensMb.adicionarMensagem(MensagemTipo.SUCCESSO,
						"O Cliente foi cadastrado com sucesso!");
				if(funcionario){
					return "index.jsf?faces-redirect=true";
				}else{
					if(compra){
						return "finalizar_compra_login.jsf?faces-redirect=true&compra=true";
					}else{
						return "index.jsf?faces-redirect=true";
					}
				}
			} else {
				mensagensMb.adicionarMensagem(MensagemTipo.ERRO,
						"A senha não confere");
				return null;
			}

		} catch (Exception excecao) {
			mensagensMb.adicionarMensagem(MensagemTipo.ERRO,
					"Ocorreu um erro durante o processamento da solicitação.");
			return "cadastro.jsf";
		}
	}
	
	/* Getters e setters */

	public Cliente getCliente() {
		return cliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
		if (id != null) {
			cliente = clienteDao.buscar(id);
		}
	}

	private boolean senhasIguais() {
		return senha1.equals(senha2);
	}
	
	public boolean isCadastro() {
		return id == null;
	}

	public String getSenha1() {
		return senha1;
	}

	public void setSenha1(String senha1) {
		this.senha1 = senha1;
	}

	public String getSenha2() {
		return senha2;
	}

	public void setSenha2(String senha2) {
		this.senha2 = senha2;
	}


	public boolean isCompra() {
		return compra;
	}

	public void setCompra(boolean compra) {
		this.compra = compra;
	}
	
	public Estado[] getEstados() {
		return Estado.values();
	}
	
	
}