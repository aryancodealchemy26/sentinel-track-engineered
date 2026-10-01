package com.sentineltrack.sentineltrack.monitor.infrastructure;

import com.sentineltrack.sentineltrack.monitor.domain.MonitoredService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitoredServiceRepository
        extends JpaRepository<MonitoredService, Long> {
            
}