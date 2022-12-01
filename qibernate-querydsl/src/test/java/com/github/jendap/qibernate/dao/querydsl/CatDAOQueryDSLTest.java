package com.github.jendap.qibernate.dao.querydsl;

import com.github.jendap.qibernate.dao.jpa.CatDAOJPATestBase;
import org.junit.jupiter.api.Test;

public class CatDAOQueryDSLTest extends CatDAOJPATestBase {
    @Test
    public void testCatDAOGenericJPADAO() {
        this.testCatDao(new CatDAOQueryDSLImpl(this.getEntityManager()));
    }
}
