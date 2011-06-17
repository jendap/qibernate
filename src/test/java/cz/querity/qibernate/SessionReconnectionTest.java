package cz.querity.qibernate;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.BeforeClass;
import org.junit.Test;

import cz.querity.qibernate.dao.CatDAO;
import cz.querity.qibernate.dao.hibernate.CatDAOCriteriaAPIImpl;
import cz.querity.qibernate.model.Cat;
import cz.querity.qibernate.model.Kitten;
import cz.querity.qibernate.model.Nest;

public class SessionReconnectionTest {
	private static final String QTLESKAVACKA = "Qtleskavacka";

	private static SessionFactory sessionFactory;

	@BeforeClass
	public static void setUpClass() {
		sessionFactory = HibernateUtil.getSessionFactory();

		final Session session = sessionFactory.openSession();
		final Transaction transaction = session.beginTransaction();

		final Nest nest0 = new Nest("Qest0", "Qere");
		final Nest nest1 = new Nest("Qest1", "Qere");
		session.save(nest0);
		session.save(nest1);

		final Cat cat0 = new Cat(QTLESKAVACKA, nest0, 1005);
		final Cat cat1 = new Cat("Q", nest1, 1050);
		session.save(cat0);
		session.save(cat1);

		final Kitten kitten0 = new Kitten(cat0, 1000);
		final Kitten kitten1 = new Kitten(cat0, 1100);
		session.save(kitten0);
		session.save(kitten1);

		transaction.commit();
		session.close();
	}

	private Cat fetchCat() {
		final Session session = sessionFactory.openSession();
		final Transaction transaction = session.beginTransaction();

		final CatDAO dao = new CatDAOCriteriaAPIImpl(session);
		final List<Cat> catsByName = dao.findByName(QTLESKAVACKA);
		assertEquals(1, catsByName.size());
		final Cat cat = catsByName.get(0);
		assertEquals(QTLESKAVACKA, cat.getName());

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
		assertEquals(1000, kitten.getPrice());
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
		assertEquals(1000, kitten.getPrice());

		kitten.setPrice(1111);
		this.mergeAndTestKitten(kitten);
	}

	private void mergeAndTestKitten(final Kitten kitten) {
		final Session session = sessionFactory.openSession();
		final Transaction transaction = session.beginTransaction();

		final Kitten mergedKitten = (Kitten) session.merge(kitten);
		assertEquals("Qest0", mergedKitten.getCat().getNest().getName());

		transaction.rollback();
		session.close();
	}
}
