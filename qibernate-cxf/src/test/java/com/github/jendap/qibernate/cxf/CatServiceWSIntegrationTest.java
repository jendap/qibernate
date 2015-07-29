package com.github.jendap.qibernate.cxf;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.Response;

public class CatServiceWSIntegrationTest {
	private AsyncHttpClientConfig config;
	private AsyncHttpClient client;

	@Before
	public void setUp() {
		this.config = new AsyncHttpClientConfig.Builder()
//				.setMaximumConnectionsPerHost(CONNECTIONS_PER_HOST)
//				.setConnectionTimeoutInMs(CONNECTION_TIMEOUT_IN_MS)
				.build();
		this.client = new AsyncHttpClient(this.config);
	}

	@Test
	public void testFeedAllStarvingCats() throws Exception {
//		System.in.read();
		final Response response = client.preparePost("http://localhost:8080/").execute().get();
		assertEquals("Not Found", response.getStatusText());
//		final String response = serviceClient.feedAllStarvingCats();
//		assertEquals("Qk", response);
	}
}
