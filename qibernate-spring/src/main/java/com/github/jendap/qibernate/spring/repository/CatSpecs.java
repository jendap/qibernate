package com.github.jendap.qibernate.spring.repository;

import com.github.jendap.qibernate.model.Cat;
import com.github.jendap.qibernate.model.Cat_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CatSpecs {
    public static Specification<Cat> isStarving(final int starvingThreshold) {
        return new Specification<Cat>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Cat> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.ge(root.get(Cat_.hunger), starvingThreshold);
            }
        };
    }
}
