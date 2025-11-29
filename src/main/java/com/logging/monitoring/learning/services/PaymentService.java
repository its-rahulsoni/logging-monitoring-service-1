package com.logging.monitoring.learning.services;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final Counter paymentCounter;

    public PaymentService(MeterRegistry registry) {
        this.paymentCounter = Counter.builder("payment_requests_total")
                .description("Total payment requests")
                .register(registry);
    }

    public void processPayment() {
        paymentCounter.increment();
        // payment logic
    }
}
