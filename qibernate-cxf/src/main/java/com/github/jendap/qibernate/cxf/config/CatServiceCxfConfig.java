package com.github.jendap.qibernate.cxf.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.xml.ws.Endpoint;

import lombok.val;

import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.feature.AbstractFeature;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.github.jendap.qibernate.cxf.CatServiceRS;
import com.github.jendap.qibernate.cxf.CatServiceRSImpl;
import com.github.jendap.qibernate.cxf.CatServiceWS;
import com.github.jendap.qibernate.cxf.CatServiceWSImpl;
import com.github.jendap.qibernate.spring.config.CatServiceConfig;
import com.github.jendap.qibernate.spring.config.datasource.DataSourceConfigPackagePlaceHolder;
import com.github.jendap.qibernate.spring.repository.CatRepository;
import com.github.jendap.qibernate.spring.service.CatService;

@Configuration
@ComponentScan(basePackageClasses = { DataSourceConfigPackagePlaceHolder.class })
@Import({ CatServiceConfig.class })
//@Import({ SpringTestBaseConfig.class })
public class CatServiceCxfConfig {
	@Inject
	CatService catService;

	@Inject
	CatRepository catRepository;

	@Bean
	public CatServiceRS catServiceRS() {
		return new CatServiceRSImpl(catRepository, catService);
	}

	@Bean
	public CatServiceWS catServiceWS() {
		return new CatServiceWSImpl(catRepository, catService);
	}

	@Bean
	public JaxWsServerFactoryBean jaxWsServerFactoryBean() {
		val serverFactory = new JaxWsServerFactoryBean();
		serverFactory.setFeatures(this.cxfFeatures());
		serverFactory.setServiceBean(this.catServiceWS());
//		serverFactory.getInInterceptors().add(new MyInInterceptor());
		serverFactory.setAddress("/catServiceWS");
		return serverFactory;
	}

	@Bean
	public JAXRSServerFactoryBean jaxRsServerFactoryBean() {
		val serverFactory = new JAXRSServerFactoryBean();
		serverFactory.setFeatures(this.cxfFeatures());
		serverFactory.setServiceBean(this.catServiceRS());
		serverFactory.setProvider(this.jsonProvider());
		serverFactory.setExtensionMappings(this.rsExtensionMapping());
		serverFactory.setAddress("/catServiceRS");
		return serverFactory;
	}

	@Bean
	public JacksonJsonProvider jsonProvider() {
		return new JacksonJsonProvider();
	}

	@Bean
	public List<AbstractFeature> cxfFeatures() {
		val cxfFeatures = new ArrayList<AbstractFeature>();
		cxfFeatures.add(new LoggingFeature());
		return cxfFeatures;
	}

	private Map<Object, Object> rsExtensionMapping() {
		val rsExtensionMapping = new HashMap<Object, Object>();
		rsExtensionMapping.put("xml", "application/xml");
		rsExtensionMapping.put("json", "application/json");
		return rsExtensionMapping;
	}

	@Bean
	public Endpoint catServiceWSEndpoint() {
		val endpoint = new EndpointImpl(this.catServiceWS());
		endpoint.setAddress("/catServiceWS");
		return endpoint;
	}

	// this is imported from "classpath:META-INF/cxf/cxf.xml"
	@Bean(destroyMethod = "shutdown")
	public SpringBus cxf() {
		return new SpringBus();
	}
}
