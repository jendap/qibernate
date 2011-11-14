package com.github.jendap.qibernate.dao.jpa;

import org.junit.Test;

import com.github.jendap.qibernate.dao.CatDAOTestBase;


public class CatDAOJPATest extends CatDAOTestBase {
	@Test
	public void testCatDAOJPA() {
		this.catDaoTest(new CatDAOJPAImpl(CatDAOTestBase.entityManager));
	}
}
