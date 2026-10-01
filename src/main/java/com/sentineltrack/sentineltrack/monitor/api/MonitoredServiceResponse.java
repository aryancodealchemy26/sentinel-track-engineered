package com.sentineltrack.sentineltrack.monitor.api;

import com.sentineltrack.sentineltrack.monitor.domain.MonitoredService;

public record MonitoredServiceResponse(
        Long id,
        String name,
        String url,
        boolean enabled,
        int checkIntervalSeconds
) {

    public static MonitoredServiceResponse from(
            MonitoredService service) {

        return new MonitoredServiceResponse(
                service.getId(),
                service.getName(),
                service.getUrl(),
                service.isEnabled(),
                service.getCheckIntervalSeconds()
        );
    }
}
