package com.github.jendap.qibernate.dao.hibernate;

import com.github.jendap.qibernate.dao.CatDAO;
import com.github.jendap.qibernate.model.Cat;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;

public class CatDAOCriteriaAPIImpl implements CatDAO {
    private Session session;

    public CatDAOCriteriaAPIImpl() {
        this(null);
    }

    public CatDAOCriteriaAPIImpl(final Session session) {
        this.session = session;
    }

    public void setSession(final Session session) {
        this.session = session;
    }

    @Override
    public List<Cat> findByName(final String name) {
        final CriteriaBuilder cb = this.session.getCriteriaBuilder();
        final CriteriaQuery<Cat> query = cb.createQuery(Cat.class);
        final Root<Cat> root = query.from(Cat.class);
        final CriteriaQuery<Cat> criteriaQuery = query.where(cb.equal(root.get("name"), name));
        return this.session.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<Cat> findByAge(final int from, final int to) {
        final CriteriaBuilder cb = this.session.getCriteriaBuilder();
        final CriteriaQuery<Cat> query = cb.createQuery(Cat.class);
        final Root<Cat> root = query.from(Cat.class);
        final CriteriaQuery<Cat> criteriaQuery = query.where(cb.and(cb.ge(root.get("age"), from), cb.lt(root.get("age"), to)));
        return this.session.createQuery(criteriaQuery).getResultList();
    }
}
