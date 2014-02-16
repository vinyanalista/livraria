package br.ufs.livraria.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

public abstract class DAO<Entidade> implements Serializable {
	private static final long serialVersionUID = 1L;

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
		Entidade entidade = entityManager.find(classeDaEntidade, id);
		entityManager.refresh(entidade); // Evitar cache
		return entidade;
	}

	public void inserir(Entidade entidade) {
		entityManager.persist(entidade);
	}

	public List<Entidade> listar() {
		return entityManager.createQuery(
				"SELECT entidade FROM " + classeDaEntidade.getSimpleName()
						+ " entidade", classeDaEntidade).getResultList();
	}

	public abstract void remover(Entidade entidade);
	
	public void remover(Integer id){
		entityManager.remove(entityManager.find(classeDaEntidade, id));
	}
	
}