package com.github.jendap.qibernate.dao.querydsl;

import static com.github.jendap.qibernate.model.QCat.cat;

import java.util.List;

import javax.persistence.EntityManager;

import com.github.jendap.qibernate.dao.CatDAO;
import com.github.jendap.qibernate.model.Cat;
import com.mysema.query.jpa.impl.JPAQuery;


public class CatDAOQueryDSLImpl implements CatDAO {
	private final EntityManager em;

	public CatDAOQueryDSLImpl(final EntityManager entityManager) {
		this.em = entityManager;
	}

	@Override
	public List<Cat> findByName(String name) {
		return new JPAQuery(this.em).from(cat)
				.where(cat.name.eq(name)).list(cat);
	}

	@Override
	public List<Cat> findByAge(int from, int to) {
		return new JPAQuery(this.em).from(cat)
				.where(cat.age.goe(from).and(cat.age.lt(to))).list(cat);
	}
}
