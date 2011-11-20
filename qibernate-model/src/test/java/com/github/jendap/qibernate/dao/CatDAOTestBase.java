package com.github.jendap.qibernate.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.github.jendap.qibernate.model.Cat;


public class CatDAOTestBase {
	public static void assertCheerleaderCat(final CatDAOTestFixtures fixtures,
			final List<Cat> cheerleaders) {
		assertEquals(1, cheerleaders.size());
		final Cat first = cheerleaders.get(0);
		assertEquals(fixtures.getCat0().getName(), first.getName());
	}

	public static void catDaoTest(final CatDAOTestFixtures fixtures, final CatDAO dao) {
		final List<Cat> catsByName = dao.findByName(fixtures.getCat0().getName());
		CatDAOTestBase.assertCheerleaderCat(fixtures, catsByName);
		final List<Cat> catsByAge = dao.findByAge(fixtures.getCat0().getAge(),
				fixtures.getCat0().getAge() + 1);
		CatDAOTestBase.assertCheerleaderCat(fixtures, catsByAge);
	}

}
