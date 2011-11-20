package com.github.jendap.qibernate.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.github.jendap.qibernate.dao.CatDAOTestFixtures;


public class CatDAOJPATestFixtures {
	private final EntityManagerFactory entityManagerFactory;
	private final CatDAOTestFixtures fixtures;

	public CatDAOJPATestFixtures(final EntityManagerFactory entityManagerFactory,
			final CatDAOTestFixtures fixtures) {
		this.entityManagerFactory = entityManagerFactory;
		this.fixtures = fixtures;
	}

	public void createFixtures() {
		final EntityManager entityManager = entityManagerFactory.createEntityManager();
		final EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		entityManager.persist(this.fixtures.getNest0());
		entityManager.persist(this.fixtures.getCat0());
		entityManager.persist(this.fixtures.getCat1());
		entityManager.persist(this.fixtures.getKitten0());
		entityManager.persist(this.fixtures.getKitten1());

		entityTransaction.commit();
		entityManager.close();
	}

	public void removeFixtures() {
		final EntityManager entityManager = entityManagerFactory.createEntityManager();
		final EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();

		entityManager.remove(this.fixtures.getKitten1());
		entityManager.remove(this.fixtures.getKitten0());
		entityManager.remove(this.fixtures.getCat1());
		entityManager.remove(this.fixtures.getCat0());
		entityManager.remove(this.fixtures.getNest0());

		entityTransaction.commit();
		entityManager.close();
	}
}
