package com.github.jendap.qibernate.cxf.config;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class CxfWebAppInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        builder.sources(CxfRSConfig.class, CxfWSConfig.class);
        return builder;
    }
}
