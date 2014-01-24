package br.ufs.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

public abstract class DAO<Entidade> implements Serializable {
	protected static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "livraria")
	protected EntityManager entityManager;

	private final Class<Entidade> classeDaEntidade;

	public DAO(Class<Entidade> classeDaEntidade) {
		this.classeDaEntidade = classeDaEntidade;
	}

	public void atualizar(Entidade entidade) {
		entityManager.merge(entidade);
	}

	public Entidade buscar(Integer id) {
		return entityManager.find(classeDaEntidade, id);
	}

	public void inserir(Entidade entidade) {
		entityManager.persist(entidade);
	}

	public List<Entidade> listar() {
		return entityManager.createQuery(
				"SELECT entidade FROM " + classeDaEntidade.getSimpleName()
						+ " entidade", classeDaEntidade).getResultList();
	}

	public void remover(int id){
		entityManager.remove(entityManager.find(classeDaEntidade, id));
	};
}