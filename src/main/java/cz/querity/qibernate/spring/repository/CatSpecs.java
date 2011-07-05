package cz.querity.qibernate.spring.repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import cz.querity.qibernate.model.Cat;
import cz.querity.qibernate.model.Cat_;

public class CatSpecs {
	public static Specification<Cat> isCheerleader() {
		return new Specification<Cat>() {
			@Override
			public Predicate toPredicate(Root<Cat> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(root.get(Cat_.name), "eskavacka");
			}
		};
	}
}
