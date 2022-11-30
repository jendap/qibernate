package com.github.jendap.qibernate.spring.service;

import com.github.jendap.qibernate.model.Cat;
import org.springframework.transaction.annotation.Transactional;

public interface CatService {
    @Transactional
    void playCatsByName(final String catName, final int amount);

    @Transactional
    void feedAllStarvingCats(final int starvingThreshold, final int amount);

    @Transactional
    void save(final Cat cat);
}
