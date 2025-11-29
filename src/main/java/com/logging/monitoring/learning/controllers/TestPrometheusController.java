package com.logging.monitoring.learning.controllers;

import io.micrometer.prometheusmetrics.PrometheusMeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestPrometheusController {

    @Autowired
    PrometheusMeterRegistry prometheusRegistry;

    @GetMapping("/test-metrics")
    public String testMetrics() {
        return prometheusRegistry.scrape();
    }
}
