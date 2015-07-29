package com.github.jendap.qibernate.dao.hibernate;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

import com.github.jendap.qibernate.dao.CatDAO;
import com.github.jendap.qibernate.dao.CatDAOTestBase;
import com.github.jendap.qibernate.model.Cat;
import com.github.jendap.qibernate.model.Kitten;
import com.github.jendap.qibernate.model.Nest;
import com.github.jendap.qibernate.util.HibernateUtil;

public class DbCopierTest extends CatDAOTestBase {
	@Test
	public void copyCat() {
		final Session sourceSession = HibernateUtil.getSessionFactory().openSession();
		sourceSession.setDefaultReadOnly(true);
		final Transaction sourceTransaction = sourceSession.beginTransaction();
		final Session targetSession = this.buildTargetSessionFactory().openSession();
		final Transaction targetTransaction = targetSession.beginTransaction();

		final CatDAO sourceDao = new CatDAOCriteriaAPIImpl(sourceSession);
		final List<Cat> catsByName = sourceDao.findByName(this.getCat0().getName());
		assertEquals(1, catsByName.size());
		assertEquals(2, ((Number) sourceSession.createCriteria(Cat.class).setProjection(Projections.rowCount()).uniqueResult()).intValue());
		assertEquals(0, ((Number) targetSession.createCriteria(Cat.class).setProjection(Projections.rowCount()).uniqueResult()).intValue());
		final CatDAO targetDao = new CatDAOCriteriaAPIImpl(targetSession);
		assertEquals(0, targetDao.findByName(this.getCat0().getName()).size());

		final Cat cat = catsByName.get(0);

		final Mapper mapper = new DozerBeanMapper();
		final Cat copyCat = mapper.map(cat, Cat.class);
		final Nest copyNest = mapper.map(cat.getNest(), Nest.class);

//		final Cat copyCat = cat;
//		Hibernate.initialize(cat.getKittens());
//		Hibernate.initialize(cat.getNest());
//		final Nest copyNest = cat.getNest();

//		sourceSession.evict(cat);
		sourceSession.clear();

		targetSession.save(copyNest);
		targetSession.save(copyCat);

		assertEquals(1, ((Number) targetSession.createCriteria(Cat.class).setProjection(Projections.rowCount()).uniqueResult()).intValue());
		System.out.println(">>>> " + targetSession.createCriteria(Cat.class).list());

		targetTransaction.rollback();
		targetSession.close();
		sourceTransaction.rollback();
		sourceSession.close();
	}

	private SessionFactory buildTargetSessionFactory() {
		final Configuration configuration = new Configuration();
		configuration.setProperty("hibernate.connection.url", "jdbc:h2:memFS:bar;MVCC=TRUE");
		configuration.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
		configuration.setProperty("hibernate.connection.username", "sa");
		configuration.setProperty("hibernate.connection.password", "");
		configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");
		configuration.setProperty("hibernate.hbm2ddl.import_files", "");

		final ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		return configuration
				.addAnnotatedClass(Cat.class)
				.addAnnotatedClass(Kitten.class)
				.addAnnotatedClass(Nest.class)
				.buildSessionFactory(serviceRegistry);
	}
}
