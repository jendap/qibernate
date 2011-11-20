package com.github.jendap.qibernate.dao;

import lombok.Data;

import com.github.jendap.qibernate.model.Cat;
import com.github.jendap.qibernate.model.Kitten;
import com.github.jendap.qibernate.model.Nest;


@Data
public class CatDAOTestFixtures {
	private final Nest nest0;
	private final Cat cat0;
	private final Cat cat1;
	private final Kitten kitten0;
	private final Kitten kitten1;

	public CatDAOTestFixtures(final String stringPrefix) {
		this.nest0 = new Nest(stringPrefix + "nest0", stringPrefix + "here0");
		this.cat0 = new Cat(stringPrefix + "tleskavacka0", this.nest0, 0);
		this.cat1 = new Cat(stringPrefix + "tleskavacka1", this.nest0, 1);
		this.kitten0 = new Kitten(this.cat0, 0);
		this.kitten1 = new Kitten(this.cat0, 1);
	}
}
