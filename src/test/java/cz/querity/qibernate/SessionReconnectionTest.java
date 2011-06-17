package cz.querity.qibernate;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import cz.querity.qibernate.dao.CatDAO;
import cz.querity.qibernate.dao.hibernate.CatDAOCriteriaAPIImpl;
import cz.querity.qibernate.model.Cat;
import cz.querity.qibernate.model.Kitten;

public class SessionReconnectionTest {
	private static SessionFactory sessionFactory;
	private static Fixtures fixtures;

	@BeforeClass
	public static void setUpClass() {
		sessionFactory = HibernateUtil.getSessionFactory();

		fixtures = new Fixtures("SessionReconnectionTest");
		fixtures.createFixtures(sessionFactory);
	}

	@AfterClass
	public static void tearDownClass() {
		fixtures.removeFixtures(sessionFactory);
	}

	private Cat fetchCat() {
		final Session session = sessionFactory.openSession();
		final Transaction transaction = session.beginTransaction();

		final CatDAO dao = new CatDAOCriteriaAPIImpl(session);
		final List<Cat> catsByName = dao.findByName(fixtures.getCat0().getName());
		assertEquals(1, catsByName.size());
		final Cat cat = catsByName.get(0);
		assertEquals(fixtures.getCat0().getName(), cat.getName());

		transaction.rollback();
		session.close();

		return cat;
	}

	private Kitten fetchFirstKitten(final Cat cat) {
		final Session session = sessionFactory.openSession();
		final Transaction transaction = session.beginTransaction();

		session.update(cat);
		final Kitten kitten = cat.getKittens().iterator().next();

		transaction.rollback();
		session.close();

		return kitten;
	}

	@Test
	public void testSimpleCatFetch() {
		this.fetchCat();
	}

	@Test
	public void testFetchFirstKittenFromCat() {
		final Cat cat = this.fetchCat();
		final Kitten kitten = this.fetchFirstKitten(cat);
		assertEquals(fixtures.getKitten0().getPrice(), kitten.getPrice());
	}

	@Test
	public void testMergeNewKitten() {
		final Cat cat = this.fetchCat();

		final Kitten kitten = new Kitten(cat, 10);
		this.mergeAndTestKitten(kitten);
	}

	@Test
	public void testMergeModifiedKitten() {
		final Cat cat = this.fetchCat();
		final Kitten kitten = this.fetchFirstKitten(cat);
		assertEquals(fixtures.getKitten0().getPrice(), kitten.getPrice());

		kitten.setPrice(1111);
		this.mergeAndTestKitten(kitten);
	}

	private void mergeAndTestKitten(final Kitten kitten) {
		final Session session = sessionFactory.openSession();
		final Transaction transaction = session.beginTransaction();

		final Kitten mergedKitten = (Kitten) session.merge(kitten);
		assertEquals("SessionReconnectionTestnest0", mergedKitten.getCat().getNest().getName());

		transaction.rollback();
		session.close();
	}
}
