package com.github.jendap.qibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.jendap.qibernate.model.Cat;
import com.github.jendap.qibernate.model.Kitten;
import com.github.jendap.qibernate.model.Nest;


public class HibernateUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(HibernateUtil.class);

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			final Configuration configuration = new Configuration();
//			configuration.setProperty("hibernate.show_sql", "true");

			return configuration.configure()
					.addAnnotatedClass(Cat.class)
					.addAnnotatedClass(Kitten.class)
					.addAnnotatedClass(Nest.class)
					.buildSessionFactory();
		} catch (final Throwable t) {
			LOGGER.error("Initial SessionFactory creation failed.", t);
			throw new ExceptionInInitializerError(t);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
