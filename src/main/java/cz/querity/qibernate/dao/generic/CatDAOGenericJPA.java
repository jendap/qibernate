package cz.querity.qibernate.dao.generic;

import java.util.List;

import com.googlecode.genericdao.dao.jpa.GenericDAO;

import cz.querity.qibernate.model.Cat;

public interface CatDAOGenericJPA extends GenericDAO<Cat, Long> {
	public List<Cat> findByName(String name);

	public List<Cat> findByAge(int from, int to);
}
