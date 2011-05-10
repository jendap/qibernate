package cz.querity.qibernate.dao;

import java.util.List;

import cz.querity.qibernate.model.Cat;

public interface CatDAO {
	public List<Cat> findByName(String name);

	public List<Cat> findByAge(int from, int to);
}
