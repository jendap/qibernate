package com.github.jendap.qibernate.spring.config;

import com.github.jendap.qibernate.model.Cat;
import com.github.jendap.qibernate.spring.repository.CatRepository;
import com.github.jendap.qibernate.spring.service.CatService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackageClasses = {CatRepository.class})
@EntityScan(basePackageClasses = {Cat.class})
@ComponentScan(basePackageClasses = {CatRepository.class, CatService.class})
@EnableTransactionManagement
public class SpringConfig {
}
