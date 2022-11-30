package com.github.jendap.qibernate.cxf;

import jakarta.inject.Inject;
import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import static org.junit.Assert.assertEquals;

@ContextConfiguration(classes = {CatServiceWSTestConfig.class})
@ActiveProfiles({"dev"})
public class CatServiceWSTest extends AbstractJUnit4SpringContextTests {
    @Inject
    private CatServiceWS serviceClient;

    @Test
    public void testFeedAllStarvingCats() {
        final String response = serviceClient.feedAllStarvingCats();
        assertEquals("Qk", response);
    }
}
