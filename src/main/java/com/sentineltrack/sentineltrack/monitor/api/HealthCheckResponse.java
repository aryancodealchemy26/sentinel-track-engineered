package com.sentineltrack.sentineltrack.monitor.api;

import com.sentineltrack.sentineltrack.monitor.domain.HealthCheck;

import java.time.Instant;

public record HealthCheckResponse(
        Long id,
        Long serviceId,
        boolean up,
        Integer statusCode,
        Long latencyMs,
        Instant checkedAt
) {

    public static HealthCheckResponse from(
            HealthCheck healthCheck) {

        return new HealthCheckResponse(
                healthCheck.getId(),
                healthCheck.getMonitoredService().getId(),
                healthCheck.isUp(),
                healthCheck.getStatusCode(),
                healthCheck.getLatencyMs(),
                healthCheck.getCheckedAt()
        );
    }
}