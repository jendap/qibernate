package com.github.jendap.qibernate.spring.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

@NoRepositoryBean
public interface ReadOnlyRepository<T, ID extends Serializable> extends Repository<T, ID> {
    T findOne(final ID id);

    Iterable<T> findAll();

    Iterable<T> findAll(final Sort sort);

    Page<T> findAll(final Pageable pageable);
}
