package com.github.jendap.qibernate.spring.repository;

import com.github.jendap.qibernate.model.Kitten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface KittenRepository extends JpaRepository<Kitten, Long>, QuerydslPredicateExecutor<Kitten> {
}
