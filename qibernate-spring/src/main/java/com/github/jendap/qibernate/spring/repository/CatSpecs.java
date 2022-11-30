package com.github.jendap.qibernate.spring.repository;

import com.github.jendap.qibernate.model.Cat;
import com.github.jendap.qibernate.model.Cat_;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serial;

public class CatSpecs {
    public static Specification<Cat> isStarving(final int starvingThreshold) {
        return new Specification<>() {
            @Serial
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Cat> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.ge(root.get(Cat_.hunger), starvingThreshold);
            }
        };
    }
}
