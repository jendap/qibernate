package com.github.jendap.qibernate.dao.hibernate;

import com.github.jendap.qibernate.dao.CatDAOTestBase;
import com.github.jendap.qibernate.util.HibernateUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;


@Data
@EqualsAndHashCode(callSuper = true)
public class CatDAOHibernateTestBase extends CatDAOTestBase {
    private Session session;
    private Transaction transaction;

    @Before
    public void setUp() {
        this.session = HibernateUtil.getSessionFactory().openSession();
        this.transaction = this.session.beginTransaction();
    }

    @After
    public void tearDown() {
        this.transaction.rollback();
        this.session.close();
    }
}
