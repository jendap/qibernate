package com.github.jendap.qibernate.spring;

import com.github.jendap.qibernate.spring.config.SpringConfig;
import com.github.jendap.qibernate.spring.repository.CatRepository;
import jakarta.inject.Inject;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({SpringConfig.class})
public class QibernateApp implements CommandLineRunner {
    @Inject
    CatRepository catRepository;

    @Override
    public void run(String... args) {
        System.out.println("Starting " + this.getClass().getSimpleName().split("\\$\\$")[0] + " ...");
        System.out.println("catRepository.count()=" + catRepository.count());
    }

    public static void main(String[] args) {
        SpringApplication.run(QibernateApp.class, args);
    }
}
