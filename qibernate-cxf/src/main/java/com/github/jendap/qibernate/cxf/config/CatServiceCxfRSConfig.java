package com.github.jendap.qibernate.cxf.config;

import com.fasterxml.jackson.jakarta.rs.json.JacksonJsonProvider;
import com.github.jendap.qibernate.cxf.CatServiceRS;
import com.github.jendap.qibernate.cxf.CatServiceRSImpl;
import com.github.jendap.qibernate.spring.repository.CatRepository;
import com.github.jendap.qibernate.spring.service.CatService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CatServiceCxfConfig.class})
public class CatServiceCxfRSConfig {
    @Bean
    public CatServiceRS catServiceRS(final CatService catService, final CatRepository catRepository) {
        return new CatServiceRSImpl(catRepository, catService);
    }

    @Bean
    public JacksonJsonProvider jsonProvider() {
        return new JacksonJsonProvider();
    }

//    @Bean
//    public OpenApiFeature createOpenApiFeature() {
//        val openApiFeature = new OpenApiFeature();
//        openApiFeature.setPrettyPrint(true);
//        openApiFeature.setTitle("Qibernate CXF REST Application");
//        openApiFeature.setContactName("/dev/null");
//        openApiFeature.setDescription("This a demo showing hibernate, spring, cxf and more...");
//        openApiFeature.setVersion("1.0.0");
//        openApiFeature.setSwaggerUiConfig(new SwaggerUiConfig().url("/services/catservice/openapi.json"));
//        return openApiFeature;
//    }
}
