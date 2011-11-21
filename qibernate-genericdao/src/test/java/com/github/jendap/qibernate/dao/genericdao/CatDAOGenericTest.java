package com.github.jendap.qibernate.dao.genericdao;

import org.hibernate.context.ManagedSessionContext;
import org.junit.Test;

import com.github.jendap.qibernate.HibernateUtil;
import com.github.jendap.qibernate.dao.hibernate.CatDAOHibernateTestBase;


public class CatDAOGenericTest extends CatDAOHibernateTestBase {
	@Test
	public void testCatDAOGenericHibernateDAO() {
		ManagedSessionContext.bind((org.hibernate.classic.Session) this.getSession());
		this.catDaoTest(new CatDAOGenericHibernateImpl(HibernateUtil.getSessionFactory()));
		ManagedSessionContext.unbind(HibernateUtil.getSessionFactory());
	}
}
