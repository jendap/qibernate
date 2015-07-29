package com.github.jendap.qibernate.dao.hibernate;

import org.junit.Test;


public class CatDAOCriteriaTest extends CatDAOHibernateTestBase {
	@Test
	public void testCatDAOCriteriaAPI() {
		this.testCatDao(new CatDAOCriteriaAPIImpl(this.getSession()));
	}
}
