package com.github.jendap.qibernate.cxf.config;

import com.github.jendap.qibernate.cxf.CatServiceWS;
import com.github.jendap.qibernate.cxf.CatServiceWSImpl;
import com.github.jendap.qibernate.spring.repository.CatRepository;
import com.github.jendap.qibernate.spring.service.CatService;
import jakarta.xml.ws.Endpoint;
import lombok.val;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CxfConfig.class})
public class CxfWSConfig {
    @Bean
    public CatServiceWS catServiceWS(final CatService catService, final CatRepository catRepository) {
        return new CatServiceWSImpl(catRepository, catService);
    }

    @Bean
    public Endpoint catServiceWSEndpoint(final SpringBus springBus, final CatServiceWS catServiceWS) {
        val endpoint = new EndpointImpl(springBus, catServiceWS);
        endpoint.publish("/catServiceWS");
        return endpoint;
    }
}
