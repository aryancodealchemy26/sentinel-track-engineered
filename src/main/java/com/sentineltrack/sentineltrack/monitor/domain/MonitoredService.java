package com.sentineltrack.sentineltrack.monitor.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.Instant;

@Entity
public class MonitoredService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String url;

    private boolean enabled;

    private int checkIntervalSeconds;

    private Instant lastCheckedAt;
    
    protected MonitoredService() {
        // Required by JPA
    }

    public MonitoredService(
            String name,
            String url,
            boolean enabled,
            int checkIntervalSeconds) {

        this.name = name;
        this.url = url;
        this.enabled = enabled;
        this.checkIntervalSeconds = checkIntervalSeconds;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public int getCheckIntervalSeconds() {
        return checkIntervalSeconds;
    }

    public Instant getLastCheckedAt() {
    return lastCheckedAt;
    }

    public void markCheckedAt(Instant checkedAt) {
    this.lastCheckedAt = checkedAt;
    }
}