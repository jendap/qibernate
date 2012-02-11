package com.github.jendap.qibernate.spring.repository.xml;

import static com.github.jendap.qibernate.model.QCat.cat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.github.jendap.qibernate.model.Cat;
import com.mysema.query.jpa.impl.JPAQuery;


public class CatRepositoryQuerydslImpl implements CatRepositoryCustom {
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Cat> customFindByName(String name) {
		return new JPAQuery(this.em).from(cat)
				.where(cat.name.eq(name)).list(cat);
	}
}
