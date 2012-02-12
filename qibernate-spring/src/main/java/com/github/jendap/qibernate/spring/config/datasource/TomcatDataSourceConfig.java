package com.github.jendap.qibernate.spring.config.datasource;

import java.lang.reflect.Method;

import javax.sql.DataSource;

import lombok.val;
import lombok.extern.slf4j.Slf4j;

import org.apache.tomcat.jdbc.pool.ConnectionPool;
import org.apache.tomcat.jdbc.pool.JdbcInterceptor;
import org.apache.tomcat.jdbc.pool.PooledConnection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Tomcat jdbc pool seems to be newer and lighter than c3p0. Yet it seems to behave very well.
 */
@Configuration
@Profile("tomcat-datasource")
public class TomcatDataSourceConfig extends GenericDataSourceConfig implements DataSourceConfig {
	@Bean
	public DataSource dataSource() {
		val dataSource = new org.apache.tomcat.jdbc.pool.DataSource();

		dataSource.setDriverClassName(this.databaseDriverClass);
		dataSource.setUrl(this.databaseUrl);
		dataSource.setUsername(this.databaseUser);
		dataSource.setPassword(this.databasePassword);

		dataSource.setInitialSize(this.databasePoolMinSize);
		dataSource.setMinIdle(this.databasePoolMinSize);
		dataSource.setMaxIdle(this.databasePoolMaxSize);
		dataSource.setMaxActive(this.databasePoolMaxSize);
		dataSource.setMaxWait(4 * 1000);
//		dataSource.setTestOnBorrow(true);
//		dataSource.setTestOnReturn(true);
//		dataSource.setTestWhileIdle(true);
		dataSource.setValidationQuery("select 1");
		dataSource.setTimeBetweenEvictionRunsMillis(4 * 1000);
		dataSource.setMinEvictableIdleTimeMillis(4 * 1000);
		dataSource.setRemoveAbandoned(true);
		dataSource.setRemoveAbandonedTimeout(30);
		dataSource.setValidationInterval(4 * 1000);
		dataSource.setMaxAge(60 * 1000);

		dataSource.setDefaultAutoCommit(false);
//		dataSource.setDefaultReadOnly(false);
//		dataSource.setDefaultTransactionIsolation(READ_UNCOMMITTED);

		dataSource.setJmxEnabled(true);

//		dataSource.setJdbcInterceptors(MyTomcatPoolJdbcInterceptor.class.getName());

		return dataSource;
	}

	@Slf4j
	public static class MyTomcatPoolJdbcInterceptor extends JdbcInterceptor {

		@Override
		public void reset(final ConnectionPool parent, final PooledConnection connection) {
			log.info(">>>>>>>> reset on {}", connection);
		}

		@Override
		public Object invoke(final Object proxy, final Method method, final Object[] args)
				throws Throwable {
			log.info(">>>>>>>> invoke {} on {}", method.getName(), proxy);
			if (method.getName().equals("close")) {
				log.info(">>>>>> close({})", (Object) args);
			} else if (method.getName().equals("prepareStatement")) {
				log.info(">>>>>> prepareStatement({})", (Object) args);
			} else {
//				final TrapException te = (TrapException) proxy;
				log.info(">>>>>> other method {}", method.getName());
			}
			return super.invoke(proxy, method, args);
		}
	}
}
