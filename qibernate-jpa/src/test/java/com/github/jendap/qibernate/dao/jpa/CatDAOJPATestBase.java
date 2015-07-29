package com.github.jendap.qibernate.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.github.jendap.qibernate.dao.CatDAOTestBase;

@Data
@EqualsAndHashCode(callSuper = true)
public class CatDAOJPATestBase extends CatDAOTestBase {
	private static EntityManagerFactory entityManagerFactory;

	private EntityManager entityManager;
	private EntityTransaction entityTransaction;

	@BeforeClass
	public static void setUpClass() {
		entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
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
