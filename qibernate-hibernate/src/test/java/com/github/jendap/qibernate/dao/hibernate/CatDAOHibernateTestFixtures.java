package com.github.jendap.qibernate.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.github.jendap.qibernate.dao.CatDAOTestFixtures;


public class CatDAOHibernateTestFixtures {
	private final SessionFactory sessionFactory;
	private final CatDAOTestFixtures fixtures;

	public CatDAOHibernateTestFixtures(final SessionFactory sessionFactory,
			final CatDAOTestFixtures fixtures) {
		this.sessionFactory = sessionFactory;
		this.fixtures = fixtures;
	}

	public void createFixtures() {
		final Session session = sessionFactory.openSession();
		final Transaction transaction = session.beginTransaction();

		session.save(this.fixtures.getNest0());
		session.save(this.fixtures.getCat0());
		session.save(this.fixtures.getCat1());
		session.save(this.fixtures.getKitten0());
		session.save(this.fixtures.getKitten1());

		transaction.commit();
		session.close();
	}

	public void removeFixtures() {
		final Session session = sessionFactory.openSession();
		final Transaction transaction = session.beginTransaction();

		session.delete(this.fixtures.getKitten1());
		session.delete(this.fixtures.getKitten0());
		session.delete(this.fixtures.getCat1());
		session.delete(this.fixtures.getCat0());
		session.delete(this.fixtures.getNest0());

		transaction.commit();
		session.close();
	}
}
