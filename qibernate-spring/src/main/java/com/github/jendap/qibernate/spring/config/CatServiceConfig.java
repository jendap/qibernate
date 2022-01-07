package com.github.jendap.qibernate.spring.config;

import com.github.jendap.qibernate.spring.repository.CatRepository;
import com.github.jendap.qibernate.spring.service.CatService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackageClasses = {CatRepository.class, CatService.class})
@Import({JpaConfig.class})
@EnableTransactionManagement
public class CatServiceConfig {
}
