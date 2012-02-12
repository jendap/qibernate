package com.github.jendap.qibernate.spring;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({ "tomcat-datasource" })
public class CatServiceWithTomcatDataSourceTest extends CatServiceSpringTest {
}
