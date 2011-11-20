package com.github.jendap.qibernate.dao.jpa;

import org.junit.Test;

import com.github.jendap.qibernate.dao.CatDAOTestBase;


public class CatDAOJPATest extends CatDAOJPATestBase {
	@Test
	public void testCatDAOJPA() {
		CatDAOTestBase.catDaoTest(fixtures, new CatDAOJPAImpl(this.getEntityManager()));
	}
}
