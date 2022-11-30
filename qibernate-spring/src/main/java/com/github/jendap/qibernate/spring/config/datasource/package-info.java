/**
 * We need to configure {@link javax.sql.DataSource} in our spring
 * {@link org.springframework.context.ApplicationContext}.
 * The {@link javax.sql.DataSource} is the connection to the database.
 * It may be created from raw jdbc driver or wrapped a connection pool.
 * There may be a cache, logging and other goodies. All that have multiple
 * implementations and all of them have lots of configurations. Hence,
 * there are a few examples here.
 */
package com.github.jendap.qibernate.spring.config.datasource;
