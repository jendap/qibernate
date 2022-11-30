package com.github.jendap.qibernate.dao.querydsl;

import com.github.jendap.qibernate.dao.CatDAO;
import com.github.jendap.qibernate.model.Cat;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.github.jendap.qibernate.model.QCat.cat;

public class CatDAOQueryDSLImpl implements CatDAO {
    private final EntityManager em;

    public CatDAOQueryDSLImpl(final EntityManager entityManager) {
        this.em = entityManager;
    }

    @Override
    public List<Cat> findByName(String name) {
        return new JPAQuery<Cat>(this.em).from(cat)
//				.join(cat.kittens).fetch() // BEWARE: paging and fetch joins are undefined
//				.setFlushMode(jakarta.persistence.FlushModeType.COMMIT) // no dirty checking and state flushing
//				.setHint("org.hibernate.fetchSize", 50) // rows fetched per round trip
//				.setHint("org.hibernate.cacheable", true) // query cacheable in 2nd level cache
//				hibernate.jdbc.batch_size = 100, hibernate.order_inserts = true, hibernate.order_updates = true
//				open stateless session - BEWARE of dataloss on last segment - HHH-4042
//@Entity(dynamicUpdate = true)
                .where(cat.name.eq(name)).fetch();
    }

    @Override
    public List<Cat> findByAge(int from, int to) {
        return new JPAQuery<Cat>(this.em).from(cat).where(cat.age.goe(from).and(cat.age.lt(to))).fetch();
    }
}
