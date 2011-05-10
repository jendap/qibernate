package cz.querity.qibernate.dao.generic;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;

import com.googlecode.genericdao.dao.jpa.GenericDAOImpl;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.hibernate.HibernateMetadataUtil;
import com.googlecode.genericdao.search.jpa.JPASearchProcessor;

import cz.querity.qibernate.dao.CatDAO;
import cz.querity.qibernate.model.Cat;

public class CatDAOGenericJPAImpl extends GenericDAOImpl<Cat, Long>
		implements CatDAO, CatDAOGenericJPA {
	public CatDAOGenericJPAImpl(final SessionFactory sessionFactory, final EntityManager entityManager) {
		final HibernateMetadataUtil metadataUtil = HibernateMetadataUtil.getInstanceForSessionFactory(sessionFactory);
		final JPASearchProcessor searchProcessor = new JPASearchProcessor(metadataUtil);
		super.setSearchProcessor(searchProcessor);
		super.setEntityManager(entityManager);
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
