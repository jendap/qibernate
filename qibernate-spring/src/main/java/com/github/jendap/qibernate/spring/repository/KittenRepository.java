package com.github.jendap.qibernate.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.github.jendap.qibernate.model.Kitten;

public interface KittenRepository extends JpaRepository<Kitten, Long>, QueryDslPredicateExecutor<Kitten> {
}
