package br.ufs.livraria.mb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.ufs.livraria.dao.BoletoDAO;
import br.ufs.livraria.dao.ClienteDAO;
import br.ufs.livraria.modelo.Boleto;
import br.ufs.livraria.modelo.Cliente;

@Named
@RequestScoped
public class HistoricoClienteMB implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ClienteDAO clienteDao;

	@EJB
	private BoletoDAO boletoDao;

	private Cliente cliente;

	private Integer idCliente;

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void carregarCliente() {
		if (idCliente == null) {
			cliente = new Cliente();
		} else {
			cliente = clienteDao.buscar(idCliente);
			if (cliente == null)
				cliente = new Cliente();
		}
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
