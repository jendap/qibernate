package com.github.jendap.qibernate.spring.repository;

import com.github.jendap.qibernate.model.Cat;
import com.github.jendap.qibernate.model.Cat_;
import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static com.github.jendap.qibernate.model.QCat.cat;

public class CatRepositoryImpl implements CatRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Cat> customFindByNameUsingJPA(final String name) {
        final CriteriaBuilder cb = this.em.getCriteriaBuilder();
        final CriteriaQuery<Cat> query = cb.createQuery(Cat.class);
        final Root<Cat> cat = query.from(Cat.class);
        query.where(cb.equal(cat.get(Cat_.name), name));
        final TypedQuery<Cat> typedQuery = this.em.createQuery(query);
        final List<Cat> resultList = typedQuery.getResultList();
        return resultList;
    }

    @Override
    public List<Cat> customFindByNameUsingQueryDSL(final String name) {
        return new JPAQuery<Cat>(this.em).from(cat).where(cat.name.eq(name)).fetch();
    }
}
