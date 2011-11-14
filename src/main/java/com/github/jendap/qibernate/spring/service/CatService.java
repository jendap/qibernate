package com.github.jendap.qibernate.spring.service;

import org.springframework.transaction.annotation.Transactional;

import com.github.jendap.qibernate.model.Cat;


public interface CatService {
	@Transactional
	public void clapCheerleaders();

	@Transactional
	public void save(final Cat cat);
}
