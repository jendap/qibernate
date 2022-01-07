package com.github.jendap.qibernate.spring.service;

import com.github.jendap.qibernate.model.Cat;
import com.github.jendap.qibernate.model.Kitten;
import com.github.jendap.qibernate.spring.repository.CatRepository;
import com.github.jendap.qibernate.spring.repository.CatSpecs;
import com.github.jendap.qibernate.spring.repository.KittenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.github.jendap.qibernate.model.QCat.cat;
import static com.github.jendap.qibernate.model.QKitten.kitten;

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
//		Kitten aKitten = kittenRepository.findById(kittenId).get();
        Kitten aKitten = kittenRepository.findOne(kitten.id.eq(kittenId)).get();
        Cat aCat = aKitten.getCat();

        System.out.println(">>>> before delete");
        for (Kitten k : aCat.getKittens()) {
            System.out.println(k);
        }
        Kitten secondKitten = (Kitten) aCat.getKittens().toArray()[1];

        kittenRepository.delete(aKitten);
        kittenRepository.flush();
//		catRepository.flush();

        System.out.println(">>>> jdu refreshnout kocku");
        aCat.getNest().setName("zmeneny pelech");
//		em.getEntityManagerFactory().getCache().evictAll();
        em.refresh(secondKitten);
//		em.refresh(cat);
//		cat = catRepository.findOne(1L);
//		cat = em.merge(cat);
        System.out.println(">>>> after delete");
        for (Kitten k : aCat.getKittens()) {
            System.out.println(k);
        }
        System.out.println(">>>> " + aCat.getNest());
    }
}
