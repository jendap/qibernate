package com.github.jendap.qibernate.dao.generic;

import java.util.List;

import com.github.jendap.qibernate.model.Cat;
import com.googlecode.genericdao.dao.jpa.GenericDAO;


public interface CatDAOGenericJPA extends GenericDAO<Cat, Long> {
	public List<Cat> findByName(String name);

	public List<Cat> findByAge(int from, int to);
}
