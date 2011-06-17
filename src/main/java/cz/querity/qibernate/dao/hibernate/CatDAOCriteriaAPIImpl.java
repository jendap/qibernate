package cz.querity.qibernate.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import cz.querity.qibernate.dao.CatDAO;
import cz.querity.qibernate.model.Cat;

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
		final Property nameProperty = Property.forName("name");
		final Criteria criteria = this.session.createCriteria(Cat.class).add(
				nameProperty.eq(name));
		@SuppressWarnings("unchecked")
		final List<Cat> result = criteria.list();
		return result;
	}

	@Override
	public List<Cat> findByAge(final int from, final int to) {
		final Property ageProperty = Property.forName("age");
		final Criteria criteria = this.session.createCriteria(Cat.class).add(
				Restrictions.conjunction().add(ageProperty.ge(from))
						.add(ageProperty.lt(to)));
		@SuppressWarnings("unchecked")
		final List<Cat> result = criteria.list();
		return result;
	}
}
