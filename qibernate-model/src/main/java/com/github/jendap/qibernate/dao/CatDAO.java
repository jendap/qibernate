package com.github.jendap.qibernate.dao;

import com.github.jendap.qibernate.model.Cat;

import java.util.List;

public interface CatDAO {
    public List<Cat> findByName(String name);

    public List<Cat> findByAge(int from, int to);
}
