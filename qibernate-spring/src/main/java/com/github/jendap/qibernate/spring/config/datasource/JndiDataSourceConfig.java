package com.github.jendap.qibernate.spring.config.datasource;

import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * JNDI in specification for service discovery. It is being used in production environments.
 * This way we can tell admins/devops that we will ask for {@link DataSource} of a given name.
 * Our java (web) application does not have to know what the db is. No need to know jdbc driver
 * class name, user name or password. The entire construction of {@link DataSource} object
 * with all of its configuration is in hands of admins/devops team.
 */
@Configuration
@Profile("jndi-datasource")
public class JndiDataSourceConfig implements DataSourceConfig {
    @Bean
    public DataSource dataSource() {
        try {
            val context = new InitialContext();
            return (DataSource) context.lookup("jdbc/FooDb");
        } catch (final NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
