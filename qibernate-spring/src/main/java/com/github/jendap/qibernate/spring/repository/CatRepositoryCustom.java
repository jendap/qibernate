package com.github.jendap.qibernate.spring.repository;

import java.util.List;

import com.github.jendap.qibernate.model.Cat;


public interface CatRepositoryCustom {
	List<Cat> customFindByName(final String name);
}