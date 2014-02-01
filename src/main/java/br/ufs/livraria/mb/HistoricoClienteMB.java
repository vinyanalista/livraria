package br.ufs.livraria.mb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.ufs.livraria.dao.ClienteDAO;
import br.ufs.livraria.dao.VendaDAO;
import br.ufs.livraria.modelo.Cliente;
import br.ufs.livraria.modelo.Venda;

@Named
@RequestScoped
public class HistoricoClienteMB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ClienteDAO clienteDao;
	
	@EJB
	private VendaDAO vendaDao;
	
	private Cliente cliente;
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<Venda> getListaDeVendas() {
		return vendaDao.listar(cliente);
	}
	
	public List<Venda> getListaDeVendasConfirmadas() {
		return vendaDao.aprovados(cliente);
	}
	
	/*public List<Venda> getListaDeVendasEmAndamento() {
		return vendaDao.emAndamento(cliente);
	}*/
	
	public List<Venda> getListaDeVendasCanceladas() {
		return vendaDao.negados(cliente);
	}

}
