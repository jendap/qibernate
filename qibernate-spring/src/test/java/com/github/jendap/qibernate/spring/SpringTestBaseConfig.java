package com.github.jendap.qibernate.spring;

import com.github.jendap.qibernate.spring.config.CatServiceConfig;
import com.github.jendap.qibernate.spring.config.datasource.DataSourceConfigPackagePlaceHolder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackageClasses = {DataSourceConfigPackagePlaceHolder.class})
@Import({CatServiceConfig.class})
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
//@Transactional
public class SpringTestBaseConfig {
}
