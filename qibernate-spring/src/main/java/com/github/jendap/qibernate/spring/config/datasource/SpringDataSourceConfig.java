package com.github.jendap.qibernate.spring.config.datasource;

import javax.sql.DataSource;

import lombok.val;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * {@link DataSource} implementation using Spring's {@link DriverManagerDataSource}.
 */
@Configuration
@Profile("spring-datasource")
//@PropertySource("classpath:/db.properties")
public class SpringDataSourceConfig extends GenericDataSourceConfig implements DataSourceConfig {
	@Bean
	public DataSource dataSource() {
		val dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(this.databaseDriverClass);
		dataSource.setUrl(this.databaseUrl);
		dataSource.setUsername(this.databaseUser);
		dataSource.setPassword(this.databasePassword);
		return dataSource;
	}
}
