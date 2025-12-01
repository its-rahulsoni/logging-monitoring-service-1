package com.logging.monitoring.learning.services;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    private int count = 0;

    @Retry(name = "spring-retry")
    public String checkStatus() {
        count++;

        if (count < 3) {
            System.out.println("Service call #" + count + " → PENDING");
            return "PENDING";
        }

        System.out.println("Service call #" + count + " → SUCCESS");
        return "SUCCESS";
    }
}