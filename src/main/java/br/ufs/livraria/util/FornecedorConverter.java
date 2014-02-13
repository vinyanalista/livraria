package br.ufs.livraria.util;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.ufs.livraria.dao.FornecedorDAO;
import br.ufs.livraria.modelo.Fornecedor;

@FacesConverter(value = "fornecedorConverter")
public class FornecedorConverter implements Converter {

	@EJB
	private FornecedorDAO fornecedorDAO;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null) {
			Integer id = Integer.valueOf(value);
			return fornecedorDAO.buscar(id);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Fornecedor fornecedor = (Fornecedor) value;
			return String.valueOf(fornecedor.getId());
		}
		return null;
	}
}
