package com.github.jendap.qibernate.spring;

import com.github.jendap.qibernate.spring.repository.CatRepository;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QibernateAppImpl implements QibernateApp {
    @Inject
    CatRepository catRepository;

    @Override
    public void run() {
        log.info("run");
        catRepository.count();
    }
}
