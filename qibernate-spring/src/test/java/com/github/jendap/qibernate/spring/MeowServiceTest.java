package com.github.jendap.qibernate.spring;

import com.github.jendap.qibernate.spring.config.SpringConfig;
import com.github.jendap.qibernate.spring.service.MeowService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest
@ContextConfiguration(classes = {SpringConfig.class})
public class MeowServiceTest {
    @Inject
    MeowService meowService;

    @Test
    public void testFoo() {
        Assertions.assertDoesNotThrow(() -> meowService.foo(1L));
    }
}
