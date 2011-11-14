package com.github.jendap.qibernate.dao;

import lombok.Data;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.github.jendap.qibernate.model.Cat;
import com.github.jendap.qibernate.model.Kitten;
import com.github.jendap.qibernate.model.Nest;


@Data
public class Fixtures {
	private final Nest nest0;
	private final Cat cat0;
	private final Cat cat1;
	private final Kitten kitten0;
	private final Kitten kitten1;

	public Fixtures(final String stringPrefix) {
		this.nest0 = new Nest(stringPrefix + "nest0", stringPrefix + "here0");
		this.cat0 = new Cat(stringPrefix + "tleskavacka0", this.nest0, 0);
		this.cat1 = new Cat(stringPrefix + "tleskavacka1", this.nest0, 1);
		this.kitten0 = new Kitten(this.cat0, 0);
		this.kitten1 = new Kitten(this.cat0, 1);
	}

	public void createFixtures(final SessionFactory sessionFactory) {
		final Session session = sessionFactory.openSession();
		final Transaction transaction = session.beginTransaction();

		session.save(this.nest0);
		session.save(this.cat0);
		session.save(this.cat1);
		session.save(this.kitten0);
		session.save(this.kitten1);

		transaction.commit();
		session.close();
	}

	public void removeFixtures(final SessionFactory sessionFactory) {
		final Session session = sessionFactory.openSession();
		final Transaction transaction = session.beginTransaction();

		session.delete(this.kitten0);
		session.delete(this.kitten1);
		session.delete(this.cat0);
		session.delete(this.cat1);
		session.delete(this.nest0);

		transaction.commit();
		session.close();
	}
}
