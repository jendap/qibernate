package cz.querity.qibernate.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import cz.querity.qibernate.dao.CatDAO;
import cz.querity.qibernate.model.Cat;

public class CatDAOCriteriaAPIImpl implements CatDAO {
	private final SessionFactory sessionFactory;

	public CatDAOCriteriaAPIImpl(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Cat> findByName(final String name) {
		final Session session = this.sessionFactory.getCurrentSession();
		final Property nameProperty = Property.forName("name");
		final Criteria criteria = session.createCriteria(Cat.class).add(
				nameProperty.eq(name));
		@SuppressWarnings("unchecked")
		final List<Cat> result = criteria.list();
		return result;
	}

	@Override
	public List<Cat> findByAge(final int from, final int to) {
		final Session session = this.sessionFactory.getCurrentSession();
		final Property ageProperty = Property.forName("age");
		final Criteria criteria = session.createCriteria(Cat.class).add(
				Restrictions.disjunction().add(ageProperty.isNull())
						.add(ageProperty.between(from, to)));
		@SuppressWarnings("unchecked")
		final List<Cat> result = criteria.list();
		return result;
	}
}
