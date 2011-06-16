package cz.querity.qibernate;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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

public class CatDAOTest {
	private static SessionFactory sessionFactory;
	private static EntityManagerFactory entityManagerFactory;

	private Transaction transaction;
	private EntityManager entityManager;

	@BeforeClass
	public static void setUpClass() {
		sessionFactory = HibernateUtil.getSessionFactory();
		entityManagerFactory = Persistence.createEntityManagerFactory("manager1");

		final Session session = sessionFactory.getCurrentSession();
		final Transaction transaction = session.beginTransaction();

   		final Cat cat0 = new Cat("roztleskavacka", null, 5);
   		final Cat cat1 = new Cat("foo", "bar", 50);
		session.save(cat0);
		session.save(cat1);

		final Kitten kitten0 = new Kitten(cat0, 0);
		final Kitten kitten1 = new Kitten(cat0, 100);
		session.save(kitten0);
		session.save(kitten1);

		transaction.commit();
	}

//	@AfterClass
//	public static void tearDownClass() {
//		entityManagerFactory.close();
//		sessionFactory.close();
//	}

	@Before
	public void setUp() {
		this.transaction = sessionFactory.getCurrentSession().beginTransaction();
		this.entityManager = entityManagerFactory.createEntityManager();
	}

	@After
	public void tearDown() {
		this.transaction.rollback();
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
		this.catDaoTest(new CatDAOCriteriaAPIImpl(sessionFactory));
	}

	@Test
	public void testCatDAOGenericHibernateDAO() {
		this.catDaoTest(new CatDAOGenericHibernateImpl(sessionFactory));
	}

	@Test
	public void testCatDAOGenericJPADAO() {
		this.catDaoTest(new CatDAOGenericJPAImpl(sessionFactory, this.entityManager));
	}

	@Test
	public void testCatDAOHQL() {
		this.catDaoTest(new CatDAOHQLImpl(sessionFactory));
	}

	@Test
	public void testCatDAOJPA() {
		this.catDaoTest(new CatDAOJPAImpl(this.entityManager));
	}
}
