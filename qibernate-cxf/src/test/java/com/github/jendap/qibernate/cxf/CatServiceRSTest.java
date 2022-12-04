package com.github.jendap.qibernate.cxf;

import com.github.jendap.qibernate.cxf.config.CatServiceCxfRSConfig;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CatServiceCxfRSConfig.class})
@ActiveProfiles({"dev"})
public class CatServiceRSTest {
    @Inject
    private CatServiceRS catServiceRS;

    @Test
    public void testFeedAllStarvingCats() {
        final String response = catServiceRS.feedAllStarvingCats();
        Assertions.assertEquals("Qk", response);
    }
}
