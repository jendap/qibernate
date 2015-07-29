package com.github.jendap.qibernate.spring.service;

import static com.github.jendap.qibernate.model.QCat.cat;

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
	public void playCatsByName(final String catName, final int amount) {
		for (final Cat aCat : catRepository.findAll(cat.name.eq(catName))) {
			aCat.setHunger(aCat.getHunger() + amount);
			this.save(aCat);
		}
	}

	@Override
	public void feedAllStarvingCats(final int starvingThreshold, final int amount) {
		for (final Cat aCat : catRepository.findAll(CatSpecs.isStarving(starvingThreshold))) {
			aCat.setHunger(Math.max(0, aCat.getHunger() - amount));
			this.save(aCat);
		}
	}
}
