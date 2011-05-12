package cz.querity.qibernate.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cz.querity.qibernate.dao.CatDAO;
import cz.querity.qibernate.model.Cat;

public class CatDAOHQLImpl implements CatDAO {
	private final SessionFactory sessionFactory;

	public CatDAOHQLImpl(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Cat> findByName(final String name) {
		final Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		final List<Cat> result = session
				.createQuery("from Cat where name = :name")
				.setCacheable(true)
				.setString("name", name).list();
		return result;
	}

	@Override
	public List<Cat> findByAge(final int from, final int to) {
		final Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		final List<Cat> result = session
				// .createQuery("from Cat where age > ? and age < ?")
				.createQuery("from Cat where age between ? and ?")
				.setInteger(0, from).setInteger(1, to).list();
		return result;
	}
}
