package com.sentineltrack.sentineltrack.monitor.application;

import com.sentineltrack.sentineltrack.monitor.domain.MonitoredService;
import com.sentineltrack.sentineltrack.monitor.infrastructure.MonitoredServiceRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
public class MonitoringScheduler {

    private final MonitoredServiceRepository monitoredServiceRepository;
    private final RunHealthCheckService runHealthCheckService;

    public MonitoringScheduler(
            MonitoredServiceRepository monitoredServiceRepository,
            RunHealthCheckService runHealthCheckService) {
        this.monitoredServiceRepository = monitoredServiceRepository;
        this.runHealthCheckService = runHealthCheckService;
    }

    @Scheduled(fixedRate = 5000)
    public void runDueChecks() {

        Instant now = Instant.now();

        List<MonitoredService> services =
                monitoredServiceRepository.findAll();

        for (MonitoredService service : services) {

            if (!service.isEnabled()) {
                continue;
            }

            if (!isDue(service, now)) {
                continue;
            }

            runHealthCheckService.execute(service.getId());

            service.markCheckedAt(now);
            monitoredServiceRepository.save(service);
        }
    }

    private boolean isDue(
            MonitoredService service,
            Instant now) {

        if (service.getLastCheckedAt() == null) {
            return true;
        }

        Instant nextCheck =
                service.getLastCheckedAt()
                        .plusSeconds(service.getCheckIntervalSeconds());

        return !now.isBefore(nextCheck);
    }
}