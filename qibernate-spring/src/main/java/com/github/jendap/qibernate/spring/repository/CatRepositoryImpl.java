package com.github.jendap.qibernate.spring.repository;

import com.github.jendap.qibernate.model.Cat;
import com.github.jendap.qibernate.model.Cat_;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

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
        return typedQuery.getResultList();
    }

    @Override
    public List<Cat> customFindByNameUsingQueryDSL(final String name) {
        return new JPAQuery<Cat>(this.em).from(cat).where(cat.name.eq(name)).fetch();
    }
}
