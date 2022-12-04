package com.github.jendap.qibernate.dao.jpa;

import com.github.jendap.qibernate.dao.CatDAOTestBase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

@Data
@EqualsAndHashCode(callSuper = true)
public class CatDAOJPATestBase extends CatDAOTestBase {
    private static EntityManagerFactory entityManagerFactory;

    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    @BeforeAll
    public static void setUpClass() {
        entityManagerFactory = Persistence.createEntityManagerFactory("qibernatePersistenceUnit");
    }

    @AfterAll
    public static void tearDownClass() {
        entityManagerFactory.close();
    }

    @BeforeEach
    public void setUp() {
        this.entityManager = entityManagerFactory.createEntityManager();
        this.entityTransaction = this.entityManager.getTransaction();
        this.entityTransaction.begin();
    }

    @AfterEach
    public void tearDown() {
        this.entityTransaction.rollback();
        this.entityManager.close();
    }
}
