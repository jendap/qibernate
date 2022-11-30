package com.github.jendap.qibernate.dao.jpa;

import com.github.jendap.qibernate.dao.CatDAO;
import com.github.jendap.qibernate.model.Cat;
import com.github.jendap.qibernate.model.Cat_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class CatDAOJPAImpl implements CatDAO {
    private final EntityManager em;
    private final CriteriaBuilder cb;

    public CatDAOJPAImpl(final EntityManager entityManager) {
        this.em = entityManager;
        this.cb = this.em.getCriteriaBuilder();
    }

    @Override
    public List<Cat> findByName(final String name) {
        final CriteriaQuery<Cat> criteriaQuery = this.cb.createQuery(Cat.class);
        final Root<Cat> from = criteriaQuery.from(Cat.class);
        final CriteriaQuery<Cat> select = criteriaQuery.select(from);
        select.where(this.cb.equal(from.get(Cat_.name), name));
        final TypedQuery<Cat> typedQuery = this.em.createQuery(select);
//		final Query typedQuery = this.ENTITY_MANAGER.createQuery("from Cat c");
        final List<Cat> resultList = typedQuery.getResultList();
        return resultList;
    }

    @Override
    public List<Cat> findByAge(final int from, final int to) {
        final CriteriaQuery<Cat> cq = this.cb.createQuery(Cat.class);
        final Root<Cat> c = cq.from(Cat.class);
        cq.where(this.cb.and(this.cb.ge(c.get(Cat_.age), from), this.cb.lt(c.get(Cat_.age), to)));
        final TypedQuery<Cat> q = this.em.createQuery(cq);
        return q.getResultList();
    }

//		first result from google:
//		http://www.ibm.com/developerworks/java/library/j-typesafejpa/

//		final CriteriaQuery<Cat> cq = this.cb.createQuery(Cat.class);
//		final Root<Cat> c = cq.from(Cat.class);
//		final Root<Kitten> o = cq.from(Kitten.class);
//		cq.where(
//				this.cb.and(
//						this.cb.or(
//								this.cb.and(
//										this.cb.isNotNull(c.get(Cat_.age)),
//										this.cb.and(this.cb.ge(c.get(Cat_.age), from), this.cb.lt(c.get(Cat_.age), to))),
//								this.cb.equal(o.get(Kitten_.price), 100)),
//						this.cb.equal(c.get(Cat_.id), o.get(Kitten_.cat))));
//		final TypedQuery<Cat> tq = this.em.createQuery(cq);
//		return tq.getResultList();

//		CriteriaQuery<Double> cq = this.cb.createQuery(Double.class);
//		Root<Account> a = cq.from(Account.class);
//		cq.select(this.cb.avg(a.get(Account_.balance)));
}
