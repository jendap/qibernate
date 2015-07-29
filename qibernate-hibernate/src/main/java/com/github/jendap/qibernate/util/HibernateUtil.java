package com.github.jendap.qibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.github.jendap.qibernate.model.Cat;
import com.github.jendap.qibernate.model.Kitten;
import com.github.jendap.qibernate.model.Nest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			final Configuration configuration = new Configuration();
			configuration.configure("/hibernate.cfg.xml");
//			configuration.setProperty("hibernate.show_sql", "true");

			final ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			return configuration.addAnnotatedClass(Cat.class).addAnnotatedClass(Kitten.class)
					.addAnnotatedClass(Nest.class).buildSessionFactory(serviceRegistry);
		} catch (final Throwable t) {
			log.error("Initial SessionFactory creation failed.", t);
			throw new ExceptionInInitializerError(t);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
