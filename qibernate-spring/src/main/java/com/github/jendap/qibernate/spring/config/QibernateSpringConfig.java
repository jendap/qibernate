package com.github.jendap.qibernate.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.github.jendap.qibernate.spring.QibernateApp;
import com.github.jendap.qibernate.spring.QibernateAppImpl;
import com.github.jendap.qibernate.spring.config.datasource.DataSourceConfigPackagePlaceHolder;

@Configuration
@ComponentScan(basePackageClasses = { DataSourceConfigPackagePlaceHolder.class })
@Import({ JpaConfig.class })
public class QibernateSpringConfig {
	@Bean
	public QibernateApp qibernateApp() {
		return new QibernateAppImpl();
	}
}
