package com.github.jendap.qibernate.spring.config;

import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import java.util.ArrayList;
import java.util.List;

/**
 * Here is configuration of properties used for spring configuration of beans. The properties are read from *.properties
 * files. The property files are read from classpath from subdirectory with the same name as Spring's active profiles.
 */
@Configuration
public class PropertiesConfig {
    private final String DEFAULT_PROFILE_NAME = "dev";

    @Value("${spring.profiles.active}")
    String activeEnvironments = "dev"; // TODO: remove hardcoded environment

    @Bean
    public PropertySourcesPlaceholderConfigurer properties() {
        val resources = new ArrayList<ClassPathResource>();
        resources.addAll(getClassPathResources("database.properties"));
        resources.addAll(getClassPathResources("database.pool.properties"));
        val pspc = new PropertySourcesPlaceholderConfigurer();
        pspc.setLocations(resources.toArray(new ClassPathResource[0]));
        pspc.setIgnoreUnresolvablePlaceholders(true);
        return pspc;
    }

    private List<ClassPathResource> getClassPathResources(final String propertyFileName) {
        val resources = new ArrayList<ClassPathResource>();
        resources.add(createClassPathResource(DEFAULT_PROFILE_NAME, propertyFileName));
        if (activeEnvironments != null) {
            for (val location : activeEnvironments.split(",")) {
                val classPathResource = createClassPathResource(location, propertyFileName);
                if (classPathResource.exists()) {
                    resources.add(classPathResource);
                }
            }
        }
        return resources;
    }

    private ClassPathResource createClassPathResource(final String location, final String propertyFileName) {
        return new ClassPathResource(location + "/" + propertyFileName);
    }
}
