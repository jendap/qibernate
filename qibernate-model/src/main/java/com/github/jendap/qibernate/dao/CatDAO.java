package com.github.jendap.qibernate.dao;

import com.github.jendap.qibernate.model.Cat;

import java.util.List;

public interface CatDAO {
    List<Cat> findByName(String name);

    List<Cat> findByAge(int from, int to);
}
