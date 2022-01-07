package com.github.jendap.qibernate.cxf.config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.ConfigurableWebApplicationContext;

/**
 * There are two ways to set the active spring profiles in the runtime.
 * <ul>
 * <li>One can define system property "spring.profiles.active". This can be done for example by
 * starting jvm with -D argument (i.e. 'java -Dspring.profiles.active=dev ...').</li>
 * <li>The other way is to set the profiles programmatically at runtime.
 * {@link ApplicationContextInitializer} can be used for this in a web app. It has to be registered
 * as contextInitializerClasses in web.xml file.</li>
 * </ul>
 */
public class ContextProfileInitializer implements
        ApplicationContextInitializer<ConfigurableWebApplicationContext> {
    public void initialize(final ConfigurableWebApplicationContext ctx) {
        final ConfigurableEnvironment environment = ctx.getEnvironment();
        final String profiles = "dev"; // TODO: add some logic for profile selection
        environment.setActiveProfiles(profiles);
    }
}
