package com.github.jendap.qibernate.dao.hibernate;

import com.github.jendap.qibernate.dao.CatDAO;
import com.github.jendap.qibernate.dao.CatDAOTestBase;
import com.github.jendap.qibernate.model.Cat;
import com.github.jendap.qibernate.model.Kitten;
import com.github.jendap.qibernate.model.Nest;
import com.github.jendap.qibernate.util.HibernateUtil;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
        assertEquals(2, this.count(sourceSession));
        assertEquals(0, this.count(targetSession));
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

        assertEquals(1, this.count(targetSession));

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

    private long count(final Session session) {
        final CriteriaBuilder cb = session.getCriteriaBuilder();
        final CriteriaQuery<Long> query = cb.createQuery(Long.class);
        final Root<Cat> root = query.from(Cat.class);
        final CriteriaQuery<Long> criteriaQuery = query.select(cb.count(root));
        return session.createQuery(criteriaQuery).getSingleResult();
    }
}
