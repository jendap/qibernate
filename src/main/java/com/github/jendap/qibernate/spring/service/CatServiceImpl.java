package com.github.jendap.qibernate.spring.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.github.jendap.qibernate.model.Cat;
import com.github.jendap.qibernate.spring.repository.CatRepository;
import com.github.jendap.qibernate.spring.repository.CatSpecs;


@Service
public class CatServiceImpl implements CatService {
	private final CatRepository catRepository;

	@Inject
	public CatServiceImpl(final CatRepository catRepository) {
		this.catRepository = catRepository;
	}

	@Override
	public void save(final Cat cat) {
		this.catRepository.save(cat);
	}

	@Override
	public void clapCheerleaders() {
		for (final Cat cat : catRepository.findAll(CatSpecs.isCheerleader())) {
			cat.setName(cat.getName() + "tleskavacka");
			catRepository.save(cat);
		}
	}
}
