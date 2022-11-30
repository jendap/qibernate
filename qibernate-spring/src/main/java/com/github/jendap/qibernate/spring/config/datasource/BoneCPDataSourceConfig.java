package com.github.jendap.qibernate.spring.config.datasource;

import com.jolbox.bonecp.BoneCPDataSource;
import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

@Configuration
@Profile("bonecp-datasource")
public class BoneCPDataSourceConfig extends GenericDataSourceConfig implements DataSourceConfig {
    @Bean
    public DataSource dataSource() {
        val dataSource = new BoneCPDataSource();

        dataSource.setDriverClass(this.databaseDriverClass);
        dataSource.setJdbcUrl(this.databaseUrl);
        dataSource.setUser(this.databaseUser);
        dataSource.setPassword(this.databasePassword);

        val partitions = 3;
        dataSource.setIdleMaxAge(4, TimeUnit.SECONDS);
        dataSource.setIdleConnectionTestPeriod(4, TimeUnit.SECONDS);
        dataSource.setPartitionCount(partitions);
        dataSource.setAcquireIncrement(4);
        dataSource.setMaxConnectionsPerPartition(this.databasePoolMaxSize / partitions);
        dataSource.setMinConnectionsPerPartition(this.databasePoolMinSize / partitions);
        dataSource.setStatementsCacheSize(100);
        dataSource.setMaxConnectionAge(4, TimeUnit.SECONDS);

        dataSource.setDefaultAutoCommit(false);
//		dataSource.setDefaultReadOnly(false);
//		dataSource.setDefaultTransactionIsolation(READ_UNCOMMITTED);

        return dataSource;
    }
}
