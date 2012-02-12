package com.github.jendap.qibernate.spring.config.datasource;

import javax.sql.DataSource;

import lombok.val;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * c3p0 is mature proven jdbc pool.
 */
@Configuration
@Profile("c3p0-datasource")
public class C3p0DataSourceConfig extends GenericDataSourceConfig implements DataSourceConfig {
	@Bean
	public DataSource dataSource() throws Exception {
		val dataSource = new ComboPooledDataSource();

		dataSource.setDriverClass(this.databaseDriverClass);
		dataSource.setJdbcUrl(this.databaseUrl);
		dataSource.setUser(this.databaseUser);
		dataSource.setPassword(this.databasePassword);

		dataSource.setInitialPoolSize(this.databasePoolMinSize);
		dataSource.setMinPoolSize(this.databasePoolMinSize);
		dataSource.setMaxPoolSize(this.databasePoolMaxSize);
		dataSource.setAcquireIncrement(4);
		dataSource.setMaxStatements(4 * 1000);
		dataSource.setIdleConnectionTestPeriod(4 * 1000);
		dataSource.setCheckoutTimeout(4 * 1000);
		dataSource.setUnreturnedConnectionTimeout(4 * 1000);
		dataSource.setLoginTimeout(4 * 1000);

		return dataSource;
	}
}
