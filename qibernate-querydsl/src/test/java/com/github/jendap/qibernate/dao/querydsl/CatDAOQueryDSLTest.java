package com.github.jendap.qibernate.dao.querydsl;

import org.junit.Test;

import com.github.jendap.qibernate.dao.CatDAOTestBase;
import com.github.jendap.qibernate.dao.jpa.CatDAOJPATestBase;


public class CatDAOQueryDSLTest extends CatDAOJPATestBase {
	@Test
	public void testCatDAOGenericJPADAO() {
		CatDAOTestBase.catDaoTest(fixtures, new CatDAOQueryDSLImpl(this.getEntityManager()));
	}
}
