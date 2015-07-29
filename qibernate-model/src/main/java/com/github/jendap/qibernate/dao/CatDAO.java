package com.github.jendap.qibernate.dao;

import java.util.List;

import com.github.jendap.qibernate.model.Cat;

public interface CatDAO {
	public List<Cat> findByName(String name);

	public List<Cat> findByAge(int from, int to);
}
