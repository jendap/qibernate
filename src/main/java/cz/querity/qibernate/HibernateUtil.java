package cz.querity.qibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import cz.querity.qibernate.model.Cat;
import cz.querity.qibernate.model.Kitten;
import cz.querity.qibernate.model.Nest;

public class HibernateUtil {
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
