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
		this.nest0 = new Nest("nest0.name", "nest0.address");
		this.cat0 = new Cat(null, "cat0.name", this.nest0, 0, 10);
		this.cat1 = new Cat(null, "cat1.name", this.nest0, 1, 10);
		this.kitten0 = new Kitten(this.cat0, "kitten0.name", 0);
		this.kitten1 = new Kitten(this.cat0, "kitten1.name", 1);
	}

	public void assertOlnyCat0Found(final List<Cat> cats) {
		assertEquals(1, cats.size());
		final Cat first = cats.get(0);
		assertEquals(this.getCat0().getName(), first.getName());
	}

	public void testCatDao(final CatDAO dao) {
		final List<Cat> catsByName = dao.findByName(this.getCat0().getName());
		this.assertOlnyCat0Found(catsByName);
		final List<Cat> catsByAge = dao.findByAge(this.getCat0().getAge(),
				this.getCat0().getAge() + 1);
		this.assertOlnyCat0Found(catsByAge);
	}
}
