package com.sentineltrack.sentineltrack.monitor.infrastructure;

import com.sentineltrack.sentineltrack.monitor.domain.HealthCheck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthCheckRepository
        extends JpaRepository<HealthCheck, Long> {
}