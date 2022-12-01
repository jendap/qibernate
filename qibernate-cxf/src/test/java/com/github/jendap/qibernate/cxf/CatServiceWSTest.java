package com.github.jendap.qibernate.cxf;

import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CatServiceWSTestConfig.class})
@ActiveProfiles({"dev"})
public class CatServiceWSTest {
    @Inject
    private CatServiceWS serviceClient;

    @Test
    public void testFeedAllStarvingCats() {
        final String response = serviceClient.feedAllStarvingCats();
        Assertions.assertEquals("Qk", response);
    }
}
