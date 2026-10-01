package com.sentineltrack.sentineltrack.monitor.application;

import com.sentineltrack.sentineltrack.monitor.domain.HealthCheck;
import com.sentineltrack.sentineltrack.monitor.domain.MonitoredService;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.Instant;

@Component
public class HealthCheckExecutor {

    private final HttpClient httpClient;

    public HealthCheckExecutor() {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();
    }

    public HealthCheck execute(MonitoredService service) {

        Instant start = Instant.now();

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(service.getUrl()))
                    .timeout(Duration.ofSeconds(10))
                    .GET()
                    .build();

            HttpResponse<Void> response =
                    httpClient.send(
                            request,
                            HttpResponse.BodyHandlers.discarding()
                    );

            long latencyMs =
                    Duration.between(start, Instant.now())
                            .toMillis();

            boolean up =
                    response.statusCode() >= 200
                            && response.statusCode() < 400;

            return new HealthCheck(
                    service,
                    up,
                    response.statusCode(),
                    latencyMs,
                    Instant.now()
            );

        } catch (Exception exception) {

            long latencyMs =
                    Duration.between(start, Instant.now())
                            .toMillis();

            return new HealthCheck(
                    service,
                    false,
                    null,
                    latencyMs,
                    Instant.now()
            );
        }
    }
}