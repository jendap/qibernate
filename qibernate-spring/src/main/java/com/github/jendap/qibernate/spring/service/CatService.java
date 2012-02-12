package com.github.jendap.qibernate.spring.service;

import org.springframework.transaction.annotation.Transactional;

import com.github.jendap.qibernate.model.Cat;


public interface CatService {
	@Transactional
	public void playCatsByName(final String catName, final int amount);

	@Transactional
	public void feedAllStarvingCats(final int starvingThreshold, final int amount);

	@Transactional public void save(final Cat cat);
}
