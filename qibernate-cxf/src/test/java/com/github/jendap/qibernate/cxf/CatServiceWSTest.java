package com.github.jendap.qibernate.cxf;

import com.github.jendap.qibernate.cxf.config.CxfWSConfig;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest
@ContextConfiguration(classes = {CxfWSConfig.class})
public class CatServiceWSTest {
    @Inject
    private CatServiceWS catServiceWS;

    @Test
    public void testFeedAllStarvingCats() {
        final String response = catServiceWS.feedAllStarvingCats();
        Assertions.assertEquals("Qk", response);
    }
}
