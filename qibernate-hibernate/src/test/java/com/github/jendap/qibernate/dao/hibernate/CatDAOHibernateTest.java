package com.github.jendap.qibernate.dao.hibernate;

import org.junit.Test;

import com.github.jendap.qibernate.dao.CatDAOTestBase;

public class CatDAOHibernateTest extends CatDAOHibernateTestBase {
	@Test
	public void testCatDAOCriteriaAPI() {
		CatDAOTestBase.catDaoTest(CatDAOHibernateTestBase.fixtures,
				new CatDAOCriteriaAPIImpl(this.getSession()));
	}

	@Test
	public void testCatDAOHQL() {
		CatDAOTestBase.catDaoTest(CatDAOHibernateTestBase.fixtures,
				new CatDAOHQLImpl(this.getSession()));
	}
}
