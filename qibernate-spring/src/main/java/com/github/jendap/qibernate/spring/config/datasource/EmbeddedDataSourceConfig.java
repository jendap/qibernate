package com.github.jendap.qibernate.spring.config.datasource;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import com.github.jendap.qibernate.spring.config.annotation.DevProfile;

/**
 * For development purpose we rely on spring's implementation of {@link EmbeddedDatabaseBuilder}
 * to build an instance of datasource for us.
 */
@Configuration
@DevProfile
public class EmbeddedDataSourceConfig extends GenericDataSourceConfig implements DataSourceConfig {
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
//				.addScript("classpath:/import.sql")
				.build();
	}
}
