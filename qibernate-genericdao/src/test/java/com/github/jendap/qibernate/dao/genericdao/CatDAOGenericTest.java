package com.github.jendap.qibernate.dao.genericdao;

import org.hibernate.context.ManagedSessionContext;
import org.junit.Test;

import com.github.jendap.qibernate.dao.CatDAOTestBase;


public class CatDAOGenericTest extends CatDAOTestBase {
	@Test
	public void testCatDAOGenericHibernateDAO() {
		ManagedSessionContext.bind((org.hibernate.classic.Session) this.getSession());
		this.catDaoTest(new CatDAOGenericHibernateImpl(sessionFactory));
		ManagedSessionContext.unbind(sessionFactory);
	}

	@Test
	public void testCatDAOGenericJPADAO() {
		this.catDaoTest(new CatDAOGenericJPAImpl(sessionFactory, CatDAOGenericTest.entityManager));
	}
}
