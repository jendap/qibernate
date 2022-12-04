package com.github.jendap.qibernate.cxf;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Disabled("Why does the CFXServet not register injected CatServiceRS?")
public class CatServiceRSIT {
    private static HttpClient httpClient;

    @BeforeAll
    public static void setUp() {
        httpClient = HttpClient.newBuilder()
//                .connectTimeout(Duration.ofSeconds(20))
                .build();
    }

    @Test
    public void testFeedAllStarvingCats() throws Exception {
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/services/catServiceRS/feedAllStarvingCats"))
                .GET()
//                .setHeader("Content-Type", "application/json; charset=utf-8")
                .setHeader("Accept", "application/json")
                .build();
        final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        Assertions.assertTrue(response.body().contains("<return>Qk</return>"));
    }
}
