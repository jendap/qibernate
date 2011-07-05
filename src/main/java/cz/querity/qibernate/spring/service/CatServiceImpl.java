package cz.querity.qibernate.spring.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import cz.querity.qibernate.model.Cat;
import cz.querity.qibernate.spring.repository.CatRepository;
import cz.querity.qibernate.spring.repository.CatSpecs;

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
