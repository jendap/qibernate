package com.github.jendap.qibernate.dao.hibernate;

import com.github.jendap.qibernate.dao.CatDAO;
import com.github.jendap.qibernate.model.Cat;
import org.hibernate.Session;

import java.util.List;

public class CatDAOHQLImpl implements CatDAO {
    private Session session;

    public CatDAOHQLImpl() {
        this(null);
    }

    public CatDAOHQLImpl(final Session session) {
        this.session = session;
    }

    public void setSession(final Session session) {
        this.session = session;
    }

    @Override
    public List<Cat> findByName(final String name) {
        return this.session.createQuery("from Cat where name = :name", Cat.class)
                .setCacheable(true).setParameter("name", name).list();
    }

    @Override
    public List<Cat> findByAge(final int from, final int to) {
        return this.session.createQuery("from Cat where age >= ?1 and age < ?2", Cat.class)
                .setParameter(1, from).setParameter(2, to).list();
    }
}
