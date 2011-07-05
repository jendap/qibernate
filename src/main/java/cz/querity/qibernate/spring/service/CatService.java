package cz.querity.qibernate.spring.service;

import org.springframework.transaction.annotation.Transactional;

import cz.querity.qibernate.model.Cat;

public interface CatService {
	@Transactional
	public void clapCheerleaders();

	@Transactional
	public void save(final Cat cat);
}
