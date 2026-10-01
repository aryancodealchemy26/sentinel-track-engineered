package com.sentineltrack.sentineltrack.monitor.application;

import com.sentineltrack.sentineltrack.monitor.api.HealthCheckResponse;
import com.sentineltrack.sentineltrack.monitor.infrastructure.HealthCheckRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthCheckApplicationService {

    private final HealthCheckRepository healthCheckRepository;

    public HealthCheckApplicationService(
            HealthCheckRepository healthCheckRepository) {
        this.healthCheckRepository = healthCheckRepository;
    }

    public List<HealthCheckResponse> findHistory(Long serviceId) {
        return healthCheckRepository
                .findByMonitoredServiceIdOrderByCheckedAtDesc(serviceId)
                .stream()
                .map(HealthCheckResponse::from)
                .toList();
    }
}