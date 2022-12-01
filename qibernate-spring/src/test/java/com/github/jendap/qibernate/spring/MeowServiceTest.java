package com.github.jendap.qibernate.spring;

import com.github.jendap.qibernate.spring.service.MeowService;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class MeowServiceTest extends SpringTestBase {
    @Inject
    MeowService meowService;

    @Test
    public void testFoo() {
        Assertions.assertDoesNotThrow(() -> meowService.foo(1L));
    }
}
