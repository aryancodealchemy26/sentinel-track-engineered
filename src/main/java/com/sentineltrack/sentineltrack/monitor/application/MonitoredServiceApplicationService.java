package com.sentineltrack.sentineltrack.monitor.application;

import java.util.List;
import com.sentineltrack.sentineltrack.monitor.api.CreateMonitoredServiceRequest;
import com.sentineltrack.sentineltrack.monitor.api.MonitoredServiceResponse;
import com.sentineltrack.sentineltrack.monitor.domain.MonitoredService;
import com.sentineltrack.sentineltrack.monitor.infrastructure.MonitoredServiceRepository;
import org.springframework.stereotype.Service;

@Service
public class MonitoredServiceApplicationService {

    private final MonitoredServiceRepository repository;

    public MonitoredServiceApplicationService(
            MonitoredServiceRepository repository) {
        this.repository = repository;
    }

    public List<MonitoredServiceResponse> findAll() {
    return repository.findAll()
            .stream()
            .map(MonitoredServiceResponse::from)
            .toList();
    }
    
    public MonitoredServiceResponse create(
            CreateMonitoredServiceRequest request) {

        MonitoredService service = new MonitoredService(
                request.name(),
                request.url(),
                request.enabled(),
                request.checkIntervalSeconds()
        );

        MonitoredService saved = repository.save(service);

        return MonitoredServiceResponse.from(saved);
    }
}