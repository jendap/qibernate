package com.github.jendap.qibernate.cxf;

import com.github.jendap.qibernate.cxf.config.CxfRSConfig;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest
@ContextConfiguration(classes = {CxfRSConfig.class})
public class CatServiceRSTest {
    @Inject
    private CatServiceRS catServiceRS;

    @Test
    public void testFeedAllStarvingCats() {
        final String response = catServiceRS.feedAllStarvingCats();
        Assertions.assertEquals("Qk", response);
    }
}
