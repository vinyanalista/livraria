package br.ufs.livraria.mb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufs.livraria.dao.BoletoDAO;
import br.ufs.livraria.dao.ClienteDAO;
import br.ufs.livraria.enumeration.MensagemTipo;
import br.ufs.livraria.modelo.Boleto;
import br.ufs.livraria.modelo.Cliente;
import br.ufs.livraria.modelo.Usuario;

@Named
@RequestScoped
public class HistoricoClienteMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ClienteDAO clienteDao;

	@EJB
	private BoletoDAO boletoDao;

	private Cliente cliente;

	@Inject
	private MensagensMB mensagensMb;

	public HistoricoClienteMB() {
		this.cliente = new Cliente();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void carregarCliente(Usuario usuarioLogado) {
		if (usuarioLogado != null) {
			if (usuarioLogado instanceof Cliente) {
				cliente = (Cliente) usuarioLogado;
			}
		}
	}

	public String redirecionar() {
		mensagensMb.adicionarMensagem(MensagemTipo.ADVERTENCIA,
				"Você deve estar logado para ter acesso ao hitórico de compras!");
		return "/index.jsf?faces-redirect=true";
	}

	public List<Boleto> getListaDeBoletos() {
		return boletoDao.listar(cliente);
	}

	public List<Boleto> getListaDeBoletosConfirmados() {
		return boletoDao.confirmados(cliente);
	}

	public List<Boleto> getListaDeBoletosEmAndamento() {
		return boletoDao.emAndamento(cliente);
	}

	public List<Boleto> getListaDeBoletosCancelados() {
		return boletoDao.cancelados(cliente);
	}
}
