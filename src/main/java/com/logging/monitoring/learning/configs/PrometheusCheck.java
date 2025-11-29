package com.logging.monitoring.learning.configs;


import io.micrometer.prometheusmetrics.PrometheusMeterRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PrometheusCheck implements CommandLineRunner {

    private final PrometheusMeterRegistry registry;

    public PrometheusCheck(PrometheusMeterRegistry registry) {
        this.registry = registry;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Prometheus registry loaded: " + (registry != null));
    }
}
