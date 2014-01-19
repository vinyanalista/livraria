package br.ufs.livraria.modelo;

import static org.junit.Assert.assertNotNull;

import javax.persistence.*;

import org.junit.Test;

public class JPATest {

	private static EntityManagerFactory emf;
	private static EntityManager em;

	@Test
	public void atualizarBanco() throws Exception {
		emf = Persistence.createEntityManagerFactory("livraria_teste");
		em = emf.createEntityManager();
		assertNotNull(em);
	}

}