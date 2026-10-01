package com.sentineltrack.sentineltrack.monitor.api;

import com.sentineltrack.sentineltrack.monitor.application.MonitoredServiceApplicationService;
import com.sentineltrack.sentineltrack.monitor.application.RunHealthCheckService;
import com.sentineltrack.sentineltrack.monitor.domain.HealthCheck;
import com.sentineltrack.sentineltrack.monitor.application.HealthCheckApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class MonitoredServiceController {

    private final MonitoredServiceApplicationService applicationService;
    private final RunHealthCheckService runHealthCheckService;
    private final HealthCheckApplicationService healthCheckApplicationService;

    public MonitoredServiceController(
            MonitoredServiceApplicationService applicationService,RunHealthCheckService runHealthCheckService, HealthCheckApplicationService healthCheckApplicationService) {
        this.applicationService = applicationService;
            this.runHealthCheckService = runHealthCheckService;
            this.healthCheckApplicationService = healthCheckApplicationService;


    }

    @PostMapping("/{id}/health-checks")
    public ResponseEntity<HealthCheckResponse> runHealthCheck(
        @PathVariable Long id) {

    HealthCheck healthCheck =
            runHealthCheckService.execute(id);

    return ResponseEntity.ok(
            HealthCheckResponse.from(healthCheck)
    );
    }

    @PostMapping
    public ResponseEntity<MonitoredServiceResponse> create(
            @Valid @RequestBody CreateMonitoredServiceRequest request) {

        MonitoredServiceResponse response =
                applicationService.create(request);

        return ResponseEntity
                .status(201)
                .body(response);
    }
    @GetMapping
    public ResponseEntity<List<MonitoredServiceResponse>> findAll() {
    return ResponseEntity.ok(applicationService.findAll());
    }
    @GetMapping("/{id}/health-checks")
        public ResponseEntity<List<HealthCheckResponse>> findHistory(
        @PathVariable Long id) {

        return ResponseEntity.ok(
            healthCheckApplicationService.findHistory(id)
        );
}
}