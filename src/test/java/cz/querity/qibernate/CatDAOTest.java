package cz.querity.qibernate;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.context.ManagedSessionContext;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import cz.querity.qibernate.dao.CatDAO;
import cz.querity.qibernate.dao.generic.CatDAOGenericHibernateImpl;
import cz.querity.qibernate.dao.generic.CatDAOGenericJPAImpl;
import cz.querity.qibernate.dao.hibernate.CatDAOCriteriaAPIImpl;
import cz.querity.qibernate.dao.hibernate.CatDAOHQLImpl;
import cz.querity.qibernate.dao.jpa.CatDAOJPAImpl;
import cz.querity.qibernate.model.Cat;
import cz.querity.qibernate.model.Kitten;
import cz.querity.qibernate.model.Nest;

public class CatDAOTest {
	private static SessionFactory sessionFactory;
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	private Session session;
	private Transaction transaction;

	@BeforeClass
	public static void setUpClass() {
		sessionFactory = HibernateUtil.getSessionFactory();
		entityManagerFactory = Persistence.createEntityManagerFactory("manager1");
		entityManager = entityManagerFactory.createEntityManager();

		final Session session = sessionFactory.openSession();
		final Transaction transaction = session.beginTransaction();

		final Nest nest0 = new Nest("Nest", "Here");
		session.save(nest0);

		final Cat cat0 = new Cat("roztleskavacka", null, 5);
		final Cat cat1 = new Cat("foo", nest0, 50);
		session.save(cat0);
		session.save(cat1);

		final Kitten kitten0 = new Kitten(cat0, 0);
		final Kitten kitten1 = new Kitten(cat0, 100);
		session.save(kitten0);
		session.save(kitten1);

		transaction.commit();
		session.close();
	}

//	@AfterClass
//	public static void tearDownClass() {
//		entityManager.close();
//		entityManagerFactory.close();
//		sessionFactory.close();
//	}

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

	private void assertCheerleaderCat(final List<Cat> cheerleaders) {
		assertEquals(1, cheerleaders.size());
		final Cat first = cheerleaders.get(0);
		assertEquals("roztleskavacka", first.getName());
	}

	private void catDaoTest(final CatDAO dao) {
		final List<Cat> catsByName = dao.findByName("roztleskavacka");
		this.assertCheerleaderCat(catsByName);
		final List<Cat> catsByAge = dao.findByAge(0, 20);
		this.assertCheerleaderCat(catsByAge);
	}

	@Test
	public void testCatDAOCriteriaAPI() {
		this.catDaoTest(new CatDAOCriteriaAPIImpl(this.session));
	}

	@Test
	public void testCatDAOGenericHibernateDAO() {
		ManagedSessionContext.bind((org.hibernate.classic.Session) this.session);
		this.catDaoTest(new CatDAOGenericHibernateImpl(sessionFactory));
		ManagedSessionContext.unbind(sessionFactory);
	}

	@Test
	public void testCatDAOGenericJPADAO() {
		this.catDaoTest(new CatDAOGenericJPAImpl(sessionFactory, CatDAOTest.entityManager));
	}

	@Test
	public void testCatDAOHQL() {
		this.catDaoTest(new CatDAOHQLImpl(this.session));
	}

	@Test
	public void testCatDAOJPA() {
		this.catDaoTest(new CatDAOJPAImpl(CatDAOTest.entityManager));
	}
}
