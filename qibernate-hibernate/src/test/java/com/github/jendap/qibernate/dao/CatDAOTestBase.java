package com.github.jendap.qibernate.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import lombok.Data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.github.jendap.qibernate.HibernateUtil;
import com.github.jendap.qibernate.model.Cat;


@Data
public class CatDAOTestBase {
	protected static SessionFactory sessionFactory;
	protected static EntityManagerFactory entityManagerFactory;
	protected static EntityManager entityManager;
	protected static CatDAOTestFixtures fixtures;

	private Session session;
	private Transaction transaction;

	@BeforeClass
	public static void setUpClass() {
		sessionFactory = HibernateUtil.getSessionFactory();
		entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
		entityManager = entityManagerFactory.createEntityManager();

		fixtures = new CatDAOTestFixtures("CatDAO");
		fixtures.createFixtures(sessionFactory);
	}

	@AfterClass
	public static void tearDownClass() {
		fixtures.removeFixtures(sessionFactory);

//		entityManager.close();
//		entityManagerFactory.close();
//		sessionFactory.close();
	}

	@Before
	public void setUp() {
		this.session = sessionFactory.openSession();
		this.transaction = this.session.beginTransaction();
	}

	@After
	public void tearDown() {
		this.transaction.rollback();
		this.session.close();
	}

	protected void assertCheerleaderCat(final List<Cat> cheerleaders) {
		assertEquals(1, cheerleaders.size());
		final Cat first = cheerleaders.get(0);
		assertEquals(fixtures.getCat0().getName(), first.getName());
	}

	protected void catDaoTest(final CatDAO dao) {
		final List<Cat> catsByName = dao.findByName(fixtures.getCat0().getName());
		this.assertCheerleaderCat(catsByName);
		final List<Cat> catsByAge = dao.findByAge(fixtures.getCat0().getAge(),
				fixtures.getCat0().getAge() + 1);
		this.assertCheerleaderCat(catsByAge);
	}

}
