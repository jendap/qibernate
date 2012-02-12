package com.github.jendap.qibernate.spring.config.datasource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;

import com.github.jendap.qibernate.spring.config.PropertiesConfig;

/**
 * Properties from *.properties files are stored in java fields here. Other {@link DataSource}
 * implementations inherit from this class.
 */
@Import({ PropertiesConfig.class })
public abstract class GenericDataSourceConfig {
	@Value("${qibernate.database.driver_class}")
	protected String databaseDriverClass;

	@Value("${qibernate.database.url}")
	protected String databaseUrl;

	@Value("${qibernate.database.user}")
	protected String databaseUser;

	@Value("${qibernate.database.password}")
	protected String databasePassword;

	@Value("${qibernate.database.dialect}")
	protected String databaseDialect;

	@Value("${qibernate.database.pool.minSize}")
	protected int databasePoolMinSize;

	@Value("${qibernate.database.pool.maxSize}")
	protected int databasePoolMaxSize;
}
