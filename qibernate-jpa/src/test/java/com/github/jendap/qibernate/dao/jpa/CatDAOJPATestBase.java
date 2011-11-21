package com.github.jendap.qibernate.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import lombok.Data;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.github.jendap.qibernate.dao.CatDAOTestFixtures;


@Data
public class CatDAOJPATestBase {
	protected static EntityManagerFactory entityManagerFactory;
	protected static CatDAOTestFixtures fixtures;

	private EntityManager entityManager;
	private EntityTransaction entityTransaction;

	@BeforeClass
	public static void setUpClass() {
		entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
		fixtures = new CatDAOTestFixtures("CatDAO");
	}

	@AfterClass
	public static void tearDownClass() {
		entityManagerFactory.close();
	}

	@Before
	public void setUp() {
		this.entityManager = entityManagerFactory.createEntityManager();
		this.entityTransaction = this.entityManager.getTransaction();
		this.entityTransaction.begin();
	}

	@After
	public void tearDown() {
		this.entityTransaction.rollback();
		this.entityManager.close();
	}
}
