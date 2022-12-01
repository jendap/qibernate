package com.github.jendap.qibernate.cxf;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CatServiceWSIntegrationTest {
    private AsyncHttpClientConfig config;
    private AsyncHttpClient client;

    @BeforeEach
    public void setUp() {
        this.config = new AsyncHttpClientConfig.Builder()
//				.setMaximumConnectionsPerHost(CONNECTIONS_PER_HOST)
//				.setConnectionTimeoutInMs(CONNECTION_TIMEOUT_IN_MS)
                .build();
        this.client = new AsyncHttpClient(this.config);
    }

    @Test
    public void testFeedAllStarvingCats() throws Exception {
        final Response response = client.preparePost("http://localhost:8080/").execute().get();
        Assertions.assertEquals("Not Found", response.getStatusText());
//		final String response = serviceClient.feedAllStarvingCats();
//		Assertions.assertEquals("Qk", response);
    }
}
