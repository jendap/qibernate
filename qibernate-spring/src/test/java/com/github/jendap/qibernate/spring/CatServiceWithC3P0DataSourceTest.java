package com.github.jendap.qibernate.spring;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({ "c3p0-datasource" })
public class CatServiceWithC3P0DataSourceTest extends CatServiceSpringTest {
}
