package br.ufs.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import br.ufs.livraria.modelo.Entidade;

public class DAO<E extends Entidade> implements Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "livraria")
	protected EntityManager entityManager;

	private final Class<E> classeDaEntidade;

	public DAO(Class<E> classeDaEntidade) {
		this.classeDaEntidade = classeDaEntidade;
	}

	public void atualizar(E entidade) {
		entityManager.merge(entidade);
	}

	public E buscar(Integer id) {
		return entityManager.find(classeDaEntidade, id);
	}

	public void inserir(E entidade) {
		entityManager.persist(entidade);
	}

	public List<E> listar() {
		return entityManager.createQuery(
				"SELECT entidade FROM " + classeDaEntidade.getSimpleName()
						+ " entidade", classeDaEntidade).getResultList();
	}

	public void remover(E entidade){
		entityManager.remove(entityManager.find(classeDaEntidade, entidade.getId()));
	}
}