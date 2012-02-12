/**
 * We need to configure {@link DataSource} in our spring {@link ApplicationContext}.
 * The {@link DataSource} is represent connection to the database. There are multiple
 * different complex configurations in this package. The complexity come from the fact
 * that {@link DataSource} is constructed from jdbc driver, connection pool, cache and
 * their respective configurations. There are different pools, caches, databases and
 * each one of them has a lot of configuration options. Hence the compexity.
 */
package com.github.jendap.qibernate.spring.config.datasource;

