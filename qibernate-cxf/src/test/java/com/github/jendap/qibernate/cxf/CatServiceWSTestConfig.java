package com.github.jendap.qibernate.cxf;

import com.github.jendap.qibernate.cxf.config.CatServiceCxfConfig;
import com.github.jendap.qibernate.spring.service.CatService;
import lombok.val;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.inject.Inject;

@Configuration
@Import({CatServiceCxfConfig.class})
public class CatServiceWSTestConfig {
    private static final String CAT_SERVICE_WS_LOCAL_ADDRESS = "local://CatServiceWSTest";

    @Inject
    CatServiceRS catServiceRS;

    @Inject
    CatServiceWS catServiceWS;

    @Bean
    public JaxWsProxyFactoryBean wsProxyFactory() {
        val wsProxyFactory = new JaxWsProxyFactoryBean();
        wsProxyFactory.setServiceClass(CatService.class);
        wsProxyFactory.setAddress(CAT_SERVICE_WS_LOCAL_ADDRESS);
        return wsProxyFactory;
    }

    @Bean
    public JaxWsServerFactoryBean jaxWsServerFactoryBean() {
        val wsServerFactory = new JaxWsServerFactoryBean();
//		wsServerFactory.setServiceClass(CatServiceWSImpl.class)
        wsServerFactory.setServiceBean(catServiceWS);
        wsServerFactory.setAddress(CAT_SERVICE_WS_LOCAL_ADDRESS);
        return wsServerFactory;
    }
}
