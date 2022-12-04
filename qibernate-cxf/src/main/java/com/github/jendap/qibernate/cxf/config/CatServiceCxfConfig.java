package com.github.jendap.qibernate.cxf.config;

import com.github.jendap.qibernate.spring.config.CatServiceConfig;
import com.github.jendap.qibernate.spring.config.datasource.DataSourceConfigPackagePlaceHolder;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackageClasses = {DataSourceConfigPackagePlaceHolder.class})
@Import({CatServiceConfig.class})
//@ImportResource({"classpath:META-INF/cxf/cxf.xml", "classpath:META-INF/cxf/cxf-servlet.xml"})
public class CatServiceCxfConfig {
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
