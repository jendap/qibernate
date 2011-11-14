package com.github.jendap.qibernate.dao.generic;

import java.util.List;

import org.hibernate.SessionFactory;

import com.github.jendap.qibernate.dao.CatDAO;
import com.github.jendap.qibernate.model.Cat;
import com.googlecode.genericdao.dao.hibernate.GenericDAOImpl;
import com.googlecode.genericdao.search.Search;


public class CatDAOGenericHibernateImpl extends GenericDAOImpl<Cat, Long>
		implements CatDAO, CatDAOGenericHibernate {
	public CatDAOGenericHibernateImpl(final SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public List<Cat> findByName(final String name) {
		return this.search(new Search().addFilterEqual("name", name));
	}

	@Override
	public List<Cat> findByAge(final int from, final int to) {
		return this.search(new Search().addFilterGreaterOrEqual("age", from)
				.addFilterLessThan("age", to));
	}
}
