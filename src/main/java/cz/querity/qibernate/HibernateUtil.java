package cz.querity.qibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import cz.querity.qibernate.model.Cat;
import cz.querity.qibernate.model.Kitten;
import cz.querity.qibernate.model.Nest;


public class HibernateUtil {
/*
			config.setProperty("hibernate.dialect",
					"org.hibernate.dialect.HSQLDialect");
			config.setProperty("hibernate.connection.driver_class",
					"org.hsqldb.jdbcDriver");
			config.setProperty("hibernate.connection.url",
					"jdbc:hsqldb:mem:demodb");
			config.setProperty("hibernate.connection.username", "sa");
			config.setProperty("hibernate.connection.password", "");
			config.setProperty("hibernate.connection.pool_size", "1");
			config.setProperty("hibernate.connection.autocommit", "true");
			config.setProperty("hibernate.cache.provider_class",
					"org.hibernate.cache.NoCacheProvider");
			config.setProperty("hibernate.hbm2ddl.auto", "create-drop");
			config.setProperty("hibernate.show_sql", "true");
			config.setProperty("hibernate.transaction.factory_class",
					"org.hibernate.transaction.JDBCTransactionFactory");
			config.setProperty("hibernate.current_session_context_class",
					"thread");

			// Add your mapped classes here:
			config.addAnnotatedClass(MockObject.class);
*/
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			final Configuration configuration = new Configuration();
			return configuration.configure()
					.addAnnotatedClass(Cat.class)
					.addAnnotatedClass(Kitten.class)
					.addAnnotatedClass(Nest.class)
					.buildSessionFactory();
		} catch (final Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
