package cz.querity.qibernate.spring.webservice;

import java.util.Collection;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cz.querity.qibernate.spring.repository.CatRepository;
import cz.querity.qibernate.spring.service.CatService;
import cz.querity.qibernate.spring.service.domain.Cat;

public class CatServiceWSImpl implements CatServiceWS {
	private static final Logger log = LoggerFactory.getLogger(CatServiceRSImpl.class);

	private final CatRepository catRepository;
	private final CatService catService;

	@Inject
	public CatServiceWSImpl(final CatRepository catRepository, final CatService catService) {
		this.catRepository = catRepository;
		this.catService = catService;
	}

	@Override
	public Collection<Cat> findByName(final String name) {
		log.info("CatService.findByName({})", name);
		return Cat.fromModelCats(this.catRepository.findByName(name));
	}

	@Override
	public Collection<Cat> findByAge(final int from, final int to) {
		log.info("CatService.findByAge({}, {})", from, to);
		return Cat.fromModelCats(this.catRepository.findByAgeBetween(from, to));
	}

	@Override
	public String newCat(final Cat cat) throws RuntimeException {
		log.info("CatService.newCat({})", cat);
		this.catService.save(cat.toModelCat());
		return "Qk";
	}

	@Override
	public String clapCheerleaders() {
		this.catService.clapCheerleaders();
		return "Qk";
	}
}
