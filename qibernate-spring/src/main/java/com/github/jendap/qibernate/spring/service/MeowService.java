package com.github.jendap.qibernate.spring.service;

import static com.github.jendap.qibernate.model.QCat.cat;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.jendap.qibernate.model.Cat;
import com.github.jendap.qibernate.model.Kitten;
import com.github.jendap.qibernate.spring.repository.CatRepository;
import com.github.jendap.qibernate.spring.repository.CatSpecs;
import com.github.jendap.qibernate.spring.repository.KittenRepository;

@Service
public class MeowService {
	@Inject
	CatRepository catRepository;
	@Inject
	KittenRepository kittenRepository;

    @PersistenceContext
    private EntityManager em;

	@Transactional
	public void save(final Cat cat) {
		this.catRepository.save(cat);
	}

	@Transactional
	public void playCatsByName(final String catName, final int amount) {
		for (final Cat aCat : catRepository.findAll(cat.name.eq(catName))) {
			aCat.setHunger(aCat.getHunger() + amount);
			this.save(aCat);
		}
	}

	@Transactional
	public void feedAllStarvingCats(final int starvingThreshold, final int amount) {
		for (final Cat aCat : catRepository.findAll(CatSpecs.isStarving(starvingThreshold))) {
			aCat.setHunger(Math.max(0, aCat.getHunger() - amount));
			this.save(aCat);
		}
	}

	@Transactional
	public void foo(final Long kittenId) {
		Kitten kitten = kittenRepository.findOne(kittenId);
		Cat cat = kitten.getCat();
		
		System.out.println(">>>> before delete");
		for (Kitten k : cat.getKittens()) {
			System.out.println(k);
		}
		Kitten secondKitten = (Kitten) cat.getKittens().toArray()[1];
		
		kittenRepository.delete(kitten);
		kittenRepository.flush();
//		catRepository.flush();

		System.out.println(">>>> jdu refreshnout kocku");
		cat.getNest().setName("zmeneny pelech");
//		em.getEntityManagerFactory().getCache().evictAll();
		em.refresh(secondKitten);
//		em.refresh(cat);
//		cat = catRepository.findOne(1L);
//		cat = em.merge(cat);
		System.out.println(">>>> after delete");
		for (Kitten k : cat.getKittens()) {
			System.out.println(k);
		}
		System.out.println(">>>> " + cat.getNest());
	}
}
