package com.sentineltrack.sentineltrack.monitor.domain;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class HealthCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private MonitoredService monitoredService;

    private boolean up;

    private Integer statusCode;

    private Long latencyMs;

    private Instant checkedAt;

    protected HealthCheck() {
        // Required by JPA
    }

    public HealthCheck(
            MonitoredService monitoredService,
            boolean up,
            Integer statusCode,
            Long latencyMs,
            Instant checkedAt) {

        this.monitoredService = monitoredService;
        this.up = up;
        this.statusCode = statusCode;
        this.latencyMs = latencyMs;
        this.checkedAt = checkedAt;
    }

    public Long getId() {
        return id;
    }

    public MonitoredService getMonitoredService() {
        return monitoredService;
    }

    public boolean isUp() {
        return up;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public Long getLatencyMs() {
        return latencyMs;
    }

    public Instant getCheckedAt() {
        return checkedAt;
    }
}