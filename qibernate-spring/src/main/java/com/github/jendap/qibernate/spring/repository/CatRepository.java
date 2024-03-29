package com.github.jendap.qibernate.spring.repository;

import com.github.jendap.qibernate.model.Cat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CatRepository extends JpaRepository<Cat, Long>, QuerydslPredicateExecutor<Cat>, CatRepositoryCustom {
    List<Cat> findByName(final String name);

    Page<Cat> findByName(final String name, final Pageable pageable);

//	List<Cat> findByName(final String name, final Pageable pageable);

    List<Cat> findByName(final String name, final Sort sort);

    List<Cat> findByAgeBetween(final int from, final int to);

    @Query("select c from Cat c where c.name = ?1")
    List<Cat> customFindByNameUsingManualQuery(final String name);

    @Modifying
    @Query("update Cat c set c.age = c.age + :increment where c.name = :name")
    int growOlder(@Param("increment") final int increment, @Param("name") final String name);

    List<Cat> findAll(final Specification<Cat> specification);
}
