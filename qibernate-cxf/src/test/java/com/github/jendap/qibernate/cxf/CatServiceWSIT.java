package com.github.jendap.qibernate.cxf;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CatServiceWSIT {
    private static HttpClient httpClient;

    @BeforeAll
    public static void setUp() {
        httpClient = HttpClient.newBuilder()
//                .connectTimeout(Duration.ofSeconds(20))
                .build();
    }

    @Test
    public void testNonExistingURL() throws Exception {
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/nonExistingURL"))
                .GET()
                .build();
        final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        Assertions.assertEquals(404, response.statusCode());
    }

    @Test
    public void testFeedAllStarvingCats() throws Exception {
        final String requestBody = """
                <?xml version="1.0" encoding="utf-8"?>
                <soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
                    <soap:Header>
                    </soap:Header>
                    <soap:Body>
                        <m:feedAllStarvingCats xmlns:m="http://cxf.qibernate.jendap.github.com/" />
                    </soap:Body>
                </soap:Envelope>
                """;
        final HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://localhost:8080/services/catServiceWS"))
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .setHeader("Content-Type", "application/soap+xml; charset=utf-8")
                .build();
        final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        Assertions.assertTrue(response.body().contains("<return>Qk</return>"), response.body());
    }
}
