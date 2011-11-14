package com.github.jendap.qibernate.dao.querydsl;

import org.junit.Test;

import com.github.jendap.qibernate.dao.CatDAOTestBase;


public class CatDAOQueryDSLTest extends CatDAOTestBase {
	@Test
	public void testCatDAOGenericJPADAO() {
		this.catDaoTest(new CatDAOQueryDSLImpl(CatDAOQueryDSLTest.entityManager));
	}
}
