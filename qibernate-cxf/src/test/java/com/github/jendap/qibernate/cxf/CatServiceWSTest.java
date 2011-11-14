package com.github.jendap.qibernate.cxf;

import static org.junit.Assert.*;

import java.io.IOException;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.github.jendap.qibernate.cxf.CatServiceWS;


@ContextConfiguration({ "classpath:spring-persistance.xml", "classpath:spring-cxf-test.xml" })
public class CatServiceWSTest extends AbstractJUnit4SpringContextTests {
	@Inject
	private CatServiceWS serviceClient;

	@Test
	public void testClapCheerleaders() throws IOException {
		final String response = serviceClient.clapCheerleaders();
		assertEquals("Qk", response);
	}
}
