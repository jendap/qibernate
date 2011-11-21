package com.github.jendap.qibernate.dao.hibernate;

import lombok.Data;

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
public class CatDAOHibernateTestBase {
	protected static SessionFactory sessionFactory;
	protected static CatDAOTestFixtures fixtures;

	private Session session;
	private Transaction transaction;

	@BeforeClass
	public static void setUpClass() {
		sessionFactory = HibernateUtil.getSessionFactory();
		fixtures = new CatDAOTestFixtures("CatDAO");
	}

	@AfterClass
	public static void tearDownClass() {
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
}
