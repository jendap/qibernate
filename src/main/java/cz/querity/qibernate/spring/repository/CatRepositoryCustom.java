package cz.querity.qibernate.spring.repository;

import java.util.List;

import cz.querity.qibernate.model.Cat;

public interface CatRepositoryCustom {
	List<Cat> manualFindByName(final String name);

	List<Cat> manualFindByNameQueryDSL(final String name);
}
