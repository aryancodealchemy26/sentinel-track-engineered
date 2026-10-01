package com.sentineltrack.sentineltrack.monitor.application;

import com.sentineltrack.sentineltrack.monitor.domain.HealthCheck;
import com.sentineltrack.sentineltrack.monitor.domain.MonitoredService;
import com.sentineltrack.sentineltrack.monitor.infrastructure.HealthCheckRepository;
import com.sentineltrack.sentineltrack.monitor.infrastructure.MonitoredServiceRepository;
import org.springframework.stereotype.Service;

@Service
public class RunHealthCheckService {

    private final MonitoredServiceRepository monitoredServiceRepository;
    private final HealthCheckRepository healthCheckRepository;
    private final HealthCheckExecutor healthCheckExecutor;

    public RunHealthCheckService(
            MonitoredServiceRepository monitoredServiceRepository,
            HealthCheckRepository healthCheckRepository,
            HealthCheckExecutor healthCheckExecutor) {

        this.monitoredServiceRepository = monitoredServiceRepository;
        this.healthCheckRepository = healthCheckRepository;
        this.healthCheckExecutor = healthCheckExecutor;
    }

    public HealthCheck execute(Long serviceId) {

        MonitoredService service =
                monitoredServiceRepository.findById(serviceId)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Monitored service not found: " + serviceId
                                ));

        HealthCheck healthCheck =
                healthCheckExecutor.execute(service);

        return healthCheckRepository.save(healthCheck);
    }
}