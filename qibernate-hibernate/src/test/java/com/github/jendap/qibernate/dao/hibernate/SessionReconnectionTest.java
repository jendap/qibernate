package com.github.jendap.qibernate.dao.hibernate;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.github.jendap.qibernate.dao.CatDAO;
import com.github.jendap.qibernate.dao.CatDAOTestBase;
import com.github.jendap.qibernate.dao.hibernate.CatDAOCriteriaAPIImpl;
import com.github.jendap.qibernate.model.Cat;
import com.github.jendap.qibernate.model.Kitten;
import com.github.jendap.qibernate.util.HibernateUtil;


public class SessionReconnectionTest extends CatDAOTestBase {
	private Cat fetchCat() {
		final Session session = HibernateUtil.getSessionFactory().openSession();
		final Transaction transaction = session.beginTransaction();

		final CatDAO dao = new CatDAOCriteriaAPIImpl(session);
		final List<Cat> catsByName = dao.findByName(this.getCat0().getName());
		assertEquals(1, catsByName.size());
		final Cat cat = catsByName.get(0);
		assertEquals(this.getCat0().getName(), cat.getName());

		transaction.rollback();
		session.close();

		return cat;
	}

	private Kitten fetchFirstKitten(final Cat cat) {
		final Session session = HibernateUtil.getSessionFactory().openSession();
		final Transaction transaction = session.beginTransaction();

		session.update(cat);
		final Kitten kitten = cat.getKittens().iterator().next();

		transaction.rollback();
		session.close();

		return kitten;
	}

	private void mergeAndTestKitten(final Kitten kitten) {
		final Session session = HibernateUtil.getSessionFactory().openSession();
		final Transaction transaction = session.beginTransaction();

		final Kitten mergedKitten = (Kitten) session.merge(kitten);
		assertEquals(this.getNest0().getName(), mergedKitten.getCat().getNest().getName());

		transaction.rollback();
		session.close();
	}

	@Test
	public void testSimpleCatFetch() {
		this.fetchCat();
	}

	@Test
	public void testFetchFirstKittenFromCat() {
		final Cat cat = this.fetchCat();
		final Kitten kitten = this.fetchFirstKitten(cat);
		assertEquals(this.getKitten0().getPrice(), kitten.getPrice());
	}

	@Test
	public void testMergeNewKitten() {
		final Cat cat = this.fetchCat();

		final Kitten kitten = new Kitten(cat, "kitten.name", 10);
		this.mergeAndTestKitten(kitten);
	}

	@Test
	public void testMergeModifiedKitten() {
		final Cat cat = this.fetchCat();
		final Kitten kitten = this.fetchFirstKitten(cat);
		assertEquals(this.getKitten0().getPrice(), kitten.getPrice());

		kitten.setPrice(1111);
		this.mergeAndTestKitten(kitten);
	}
}
