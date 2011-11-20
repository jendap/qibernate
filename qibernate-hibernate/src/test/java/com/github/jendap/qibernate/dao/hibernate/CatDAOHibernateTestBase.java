package com.github.jendap.qibernate.dao.hibernate;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.github.jendap.qibernate.HibernateUtil;
import com.github.jendap.qibernate.dao.CatDAOTestFixtures;


@Data
@EqualsAndHashCode(callSuper = false)
public class CatDAOHibernateTestBase {
	protected static SessionFactory sessionFactory;
	protected static CatDAOHibernateTestFixtures catDAOHibernateTestFixtures;
	protected static CatDAOTestFixtures fixtures;

	private Session session;
	private Transaction transaction;

	@BeforeClass
	public static void setUpClass() {
		sessionFactory = HibernateUtil.getSessionFactory();
		fixtures = new CatDAOTestFixtures("CatDAOHibernate");

		catDAOHibernateTestFixtures = new CatDAOHibernateTestFixtures(sessionFactory, fixtures);
		catDAOHibernateTestFixtures.createFixtures();
	}

	@AfterClass
	public static void tearDownClass() {
		catDAOHibernateTestFixtures.removeFixtures();
		sessionFactory.close();
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
}
