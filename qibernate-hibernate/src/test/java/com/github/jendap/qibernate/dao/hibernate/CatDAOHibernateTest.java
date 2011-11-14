package com.github.jendap.qibernate.dao.hibernate;

import org.junit.Test;

import com.github.jendap.qibernate.dao.CatDAOTestBase;


public class CatDAOHibernateTest extends CatDAOTestBase {
	@Test
	public void testCatDAOCriteriaAPI() {
		this.catDaoTest(new CatDAOCriteriaAPIImpl(this.getSession()));
	}

	@Test
	public void testCatDAOHQL() {
		this.catDaoTest(new CatDAOHQLImpl(this.getSession()));
	}
}
