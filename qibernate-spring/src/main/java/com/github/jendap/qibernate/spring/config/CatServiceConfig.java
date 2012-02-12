package com.github.jendap.qibernate.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.github.jendap.qibernate.spring.repository.CatRepository;
import com.github.jendap.qibernate.spring.service.CatService;

@Configuration
@ComponentScan(basePackageClasses = { CatRepository.class, CatService.class })
@Import({ JpaConfig.class })
@EnableTransactionManagement
public class CatServiceConfig {
}
