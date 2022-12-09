package com.github.jendap.qibernate.cxf.config;

import com.github.jendap.qibernate.spring.config.SpringConfig;
import lombok.val;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.*;

@Configuration
@EnableAutoConfiguration
@Import({SpringConfig.class})
//@ImportResource({"classpath:META-INF/cxf/cxf.xml", "classpath:META-INF/cxf/cxf-servlet.xml"})
public class CxfConfig {
    @Bean
    public ServletRegistrationBean<CXFServlet> cxfServlet() {
        val servlet = new ServletRegistrationBean<>(new CXFServlet(), "/services/*");
        servlet.setLoadOnStartup(1);
        return servlet;
    }

    @Bean(name = Bus.DEFAULT_BUS_ID, destroyMethod = "shutdown")
    public SpringBus springBus() {
        return new SpringBus();
    }

//    @Bean
//    public LoggingFeature loggingFeature() {
//        val loggingFeature = new LoggingFeature();
//        loggingFeature.setPrettyLogging(true);
//        loggingFeature.setVerbose(true);
//        return loggingFeature;
//    }
//
//    @Bean
//    public MetricsFeature metricsFeature(final MetricsProvider metricsProvider) {
//        return new MetricsFeature(metricsProvider);
//    }
}
