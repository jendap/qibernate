package com.github.jendap.qibernate.spring;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringTestBaseConfig.class })
// TODO: We should use ContextHierarchy here to cache most of the beans but then we could
//       inject DataSource into JpaConfig hence we would have to create...
//		class ??? implements ApplicationListener<ContextRefreshedEvent> {
//			@Override
//			public void onApplicationEvent(final ContextRefreshedEvent event) {
//				final ApplicationContext applicationContext = ((ContextRefreshedEvent) event).getApplicationContext();
//				applicationContext.getBean(LocalContainerEntityManagerFactoryBean.class).setDataSource(dataSource);
//			}
//		}
//@ContextHierarchy({
//    @ContextConfiguration(classes = { CatServiceConfig.class }),
//    @ContextConfiguration(classes = { BoneCPConfig.class, C3p0DataSourceConfig.class, EmbeddedDataSourceConfig.class, JndiDataSourceConfig.class, SpringDataSourceConfig.class, TomcatDataSourceConfig.class })
//})
@ActiveProfiles({ "dev" })
public abstract class SpringTestBase {
}
