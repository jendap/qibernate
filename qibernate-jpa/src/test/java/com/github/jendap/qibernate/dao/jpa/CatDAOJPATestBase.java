package com.github.jendap.qibernate.dao.jpa;

import com.github.jendap.qibernate.dao.CatDAOTestBase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

@Data
@EqualsAndHashCode(callSuper = true)
public class CatDAOJPATestBase extends CatDAOTestBase {
    private static EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    @BeforeClass
    public static void setUpClass() {
        entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
    }

    @AfterClass
    public static void tearDownClass() {
        entityManagerFactory.close();
    }

    @Before
    public void setUp() {
        this.entityManager = entityManagerFactory.createEntityManager();
        this.entityTransaction = this.entityManager.getTransaction();
        this.entityTransaction.begin();
    }

    @After
    public void tearDown() {
        this.entityTransaction.rollback();
        this.entityManager.close();
    }
}
