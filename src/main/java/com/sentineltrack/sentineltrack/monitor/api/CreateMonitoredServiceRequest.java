package com.sentineltrack.sentineltrack.monitor.api;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record CreateMonitoredServiceRequest(

        @NotBlank
        String name,

        @NotBlank
        String url,

        boolean enabled,

        @Min(5)
        int checkIntervalSeconds
) {
}