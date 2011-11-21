package com.github.jendap.qibernate.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import lombok.Data;

import com.github.jendap.qibernate.model.Cat;
import com.github.jendap.qibernate.model.Kitten;
import com.github.jendap.qibernate.model.Nest;


@Data
public class CatDAOTestBase {
	private final Nest nest0;
	private final Cat cat0;
	private final Cat cat1;
	private final Kitten kitten0;
	private final Kitten kitten1;

	public CatDAOTestBase() {
		this.nest0 = new Nest("CatDAOnest0", "CatDAOhere0");
		this.cat0 = new Cat("CatDAOtleskavacka0", this.nest0, 0);
		this.cat1 = new Cat("CatDAOtleskavacka1", this.nest0, 1);
		this.kitten0 = new Kitten(this.cat0, 0);
		this.kitten1 = new Kitten(this.cat0, 1);
	}

	public void assertCheerleaderCat(final List<Cat> cheerleaders) {
		assertEquals(1, cheerleaders.size());
		final Cat first = cheerleaders.get(0);
		assertEquals(this.getCat0().getName(), first.getName());
	}

	public void catDaoTest(final CatDAO dao) {
		final List<Cat> catsByName = dao.findByName(this.getCat0().getName());
		this.assertCheerleaderCat(catsByName);
		final List<Cat> catsByAge = dao.findByAge(this.getCat0().getAge(),
				this.getCat0().getAge() + 1);
		this.assertCheerleaderCat(catsByAge);
	}

}
