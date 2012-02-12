package com.github.jendap.qibernate.spring.config;

import java.util.Properties;

import javax.inject.Inject;
import javax.sql.DataSource;

import lombok.val;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement(order = 10)
@ImportResource("classpath:/spring-persistance-springdata-repositories-only.xml")
public class JpaConfig {
	@Inject
	DataSource dataSource;

	@Bean
	public PlatformTransactionManager transactionManager() {
		return new JpaTransactionManager(entityManagerFactory().getObject());
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		val emf = new LocalContainerEntityManagerFactoryBean();
//		emf.setPersistenceUnitName("myPersistanceUnit");
		emf.setJpaDialect(this.jpaDialect());
		emf.setJpaVendorAdapter(this.jpaVendorAdapter());
		emf.setJpaProperties(this.jpaProperties());
		emf.setDataSource(this.dataSource);
//		emf.setPackagesToScan(EntityPackagePlaceHolder.class);
		return emf;
	}

	@Bean
	public JpaDialect jpaDialect() {
		return new HibernateJpaDialect();
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		val jpaVendorAdapter = new HibernateJpaVendorAdapter();
//		jpaVendorAdapter.setShowSql(true);
//		jpaVendorAdapter.setDatabase(Database.H2);
//		jpaVendorAdapter.setGenerateDdl(true);
		return jpaVendorAdapter;
	}

	@Bean
	public Properties jpaProperties() {
		val properties = new Properties();
//		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//		properties.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
//		properties.setProperty("hibernate.connection.url", "jdbc:h2:memFS:/tmp/foo;MVCC=TRUE;DB_CLOSE_ON_EXIT=FALSE");
//		properties.setProperty("hibernate.connection.username", "sa");
//		properties.setProperty("hibernate.connection.password", "");
		properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
//		properties.setProperty("hibernate.hbm2ddl.import_files", "/import.sql");
		return properties;
	}
}
