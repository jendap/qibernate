package com.github.jendap.qibernate.spring;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"bonecp-datasource"})
public class CatServiceWithBoneCPDataSourceTest extends CatServiceSpringTest {
}
