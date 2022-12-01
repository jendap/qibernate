package com.github.jendap.qibernate.dao.hibernate;

import org.junit.jupiter.api.Test;


public class CatDAOHQLTest extends CatDAOHibernateTestBase {
    @Test
    public void testCatDAOHQL() {
        this.testCatDao(new CatDAOHQLImpl(this.getSession()));
    }
}
