package com.github.jendap.qibernate.dao.genericdao;

import java.util.List;

import com.github.jendap.qibernate.model.Cat;
import com.googlecode.genericdao.dao.hibernate.GenericDAO;


public interface CatDAOGenericHibernate extends GenericDAO<Cat, Long> {
	public List<Cat> findByName(String name);

	public List<Cat> findByAge(int from, int to);
}
