package com.github.jendap.qibernate.spring;

import com.github.jendap.qibernate.spring.config.SpringConfig;
import com.github.jendap.qibernate.spring.repository.CatRepository;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({SpringConfig.class})
@Slf4j
public class QibernateApp implements CommandLineRunner {
    @Inject
    CatRepository catRepository;

    @Override
    public void run(String... args) {
        log.info("Starting {} ...", this.getClass().getName());
        catRepository.count();
    }

    public static void main(String[] args) {
        SpringApplication.run(QibernateApp.class, args);
    }
}
