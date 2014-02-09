package br.ufs.livraria.mb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.ufs.livraria.dao.LivroDAO;
import br.ufs.livraria.modelo.Livro;

@Named
@RequestScoped
public class IndexMB implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private LivroDAO livroDao;
	
	public List<Livro> maisVendidos() {
		return livroDao.maisVendidos();
	}
	
	public List<Livro> ultimosLancamentos() {
		return livroDao.ultimosLancamentos();
	}
	
}