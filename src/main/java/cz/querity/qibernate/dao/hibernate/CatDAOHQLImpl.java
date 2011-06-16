package cz.querity.qibernate.dao.hibernate;

import java.util.List;

import org.hibernate.Session;

import cz.querity.qibernate.dao.CatDAO;
import cz.querity.qibernate.model.Cat;

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
		@SuppressWarnings("unchecked")
		final List<Cat> result = this.session
				.createQuery("from Cat where name = :name")
				.setCacheable(true)
				.setString("name", name).list();
		return result;
	}

	@Override
	public List<Cat> findByAge(final int from, final int to) {
		@SuppressWarnings("unchecked")
		final List<Cat> result = this.session
				// .createQuery("from Cat where age > ? and age < ?")
				.createQuery("from Cat where age between ? and ?")
				.setInteger(0, from).setInteger(1, to).list();
		return result;
	}
}
