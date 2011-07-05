package cz.querity.qibernate.spring.repository;

import static cz.querity.qibernate.model.QCat.cat;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.mysema.query.jpa.impl.JPAQuery;

import cz.querity.qibernate.model.Cat;
import cz.querity.qibernate.model.Cat_;

public class CatRepositoryImpl implements CatRepositoryCustom {
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Cat> manualFindByName(final String name) {
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Cat> query = cb.createQuery(Cat.class);
		final Root<Cat> cat = query.from(Cat.class);
		query.where(cb.equal(cat.get(Cat_.name), name));
		final TypedQuery<Cat> typedQuery = this.em.createQuery(query);
		final List<Cat> resultList = typedQuery.getResultList();
		return resultList;
	}

	@Override
	public List<Cat> manualFindByNameQueryDSL(final String name) {
		return new JPAQuery(this.em).from(cat)
				.where(cat.name.eq(name)).list(cat);
	}
}
