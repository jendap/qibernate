package com.github.jendap.qibernate.dao.jpa;

import org.junit.jupiter.api.Test;

public class CatDAOJPATest extends CatDAOJPATestBase {
    @Test
    public void testCatDAOJPA() {
        this.testCatDao(new CatDAOJPAImpl(this.getEntityManager()));
    }
}
