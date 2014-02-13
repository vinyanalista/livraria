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
import br.ufs.livraria.enumeration.MensagemTipo;
import br.ufs.livraria.modelo.Cliente;

@Named
@RequestScoped
public class ClienteMB implements Serializable {
	private static final long serialVersionUID = 1L;

	private Cliente Cliente;

	@EJB
	private ClienteDAO ClienteDao;

	private Integer id;

	@Inject
	private MensagensMB mensagensMb;

	private String senha1;
	private String senha2;

	public ClienteMB() {
		Cliente = new Cliente();
		id = null;
	}

	public List<Cliente> getListaDeClientes() {
		return ClienteDao.listar();
	}

	/* Ações */

	public String atualizar() {
		try {
			if (senhasIguais()) {
				Cliente.setSenha(DigestUtils.sha1Hex(senha1));
				ClienteDao.atualizar(Cliente);
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

	public String inserir() {
		try {
			if (senhasIguais()) {
				Cliente.setSenha(DigestUtils.sha1Hex(senha1));
				Cliente.setDataCadastro(new Date());
				ClienteDao.inserir(Cliente);
				mensagensMb.adicionarMensagem(MensagemTipo.SUCCESSO,
						"O Cliente foi cadastrado com sucesso!");
				return "login.jsf?faces-redirect=true";
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
		return Cliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
		if (id != null) {
			Cliente = ClienteDao.buscar(id);
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

}