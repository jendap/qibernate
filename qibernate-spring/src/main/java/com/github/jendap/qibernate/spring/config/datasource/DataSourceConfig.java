package com.github.jendap.qibernate.spring.config.datasource;

import javax.sql.DataSource;

/**
 * Class implementing this interface has to create an instance of {@link DataSource}.
 */
public interface DataSourceConfig {
	DataSource dataSource() throws Exception;
}
