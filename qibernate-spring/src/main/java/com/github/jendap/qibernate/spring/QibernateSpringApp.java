package com.github.jendap.qibernate.spring;

import com.github.jendap.qibernate.spring.config.QibernateSpringConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class QibernateSpringApp {
    public static void main(final String[] args) {
        try (final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();) {
            final ConfigurableEnvironment environment = context.getEnvironment();
            environment.setActiveProfiles("tomcat-datasource", "prod");
            context.register(QibernateSpringConfig.class);
            context.refresh();

            final QibernateApp app = context.getBean(QibernateApp.class);
            app.run();
        }
    }
}
