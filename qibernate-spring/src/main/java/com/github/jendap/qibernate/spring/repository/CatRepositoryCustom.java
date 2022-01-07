package com.github.jendap.qibernate.spring.repository;

import com.github.jendap.qibernate.model.Cat;

import java.util.List;

public interface CatRepositoryCustom {
    List<Cat> customFindByNameUsingJPA(final String name);

    List<Cat> customFindByNameUsingQueryDSL(final String name);
}
