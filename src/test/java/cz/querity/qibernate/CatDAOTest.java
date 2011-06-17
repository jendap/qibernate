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
import org.junit.AfterClass;
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

public class CatDAOTest {
	private static SessionFactory sessionFactory;
	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static Fixtures fixtures;

	private Session session;
	private Transaction transaction;

	@BeforeClass
	public static void setUpClass() {
		sessionFactory = HibernateUtil.getSessionFactory();
		entityManagerFactory = Persistence.createEntityManagerFactory("manager1");
		entityManager = entityManagerFactory.createEntityManager();

		fixtures = new Fixtures("CatDAO");
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

	private void assertCheerleaderCat(final List<Cat> cheerleaders) {
		assertEquals(1, cheerleaders.size());
		final Cat first = cheerleaders.get(0);
		assertEquals(fixtures.getCat0().getName(), first.getName());
	}

	private void catDaoTest(final CatDAO dao) {
		final List<Cat> catsByName = dao.findByName(fixtures.getCat0().getName());
		this.assertCheerleaderCat(catsByName);
		final List<Cat> catsByAge = dao.findByAge(fixtures.getCat0().getAge(),
				fixtures.getCat0().getAge() + 1);
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
