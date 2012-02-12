package com.github.jendap.qibernate.spring;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringTestBaseConfig.class })
@ActiveProfiles({ "dev" })
public abstract class SpringTestBase {
}
