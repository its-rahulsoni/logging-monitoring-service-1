package com.logging.monitoring.learning.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;


@RestController
@RequestMapping("/api/crud")
public class CrudController {

    private static final Logger logger = LogManager.getLogger(CrudController.class);

    private final Random random = new Random();

    @GetMapping("/hello")
    public String hello() {
        System.out.println("Current MDC: " + ThreadContext.getImmutableContext());
        logger.info("Hello, CRUD! endpoint was called");
        logger.debug("DEBUG, CRUD! endpoint was called");
        logger.error("ERROR, CRUD! endpoint was called");
        logger.warn("WARN, CRUD! endpoint was called");
        logger.info("Hello, CRUD! endpoint was called");
        return "$$Hello, CRUD ##01!";
    }

    @GetMapping("/test")
    public String test() {
        logger.info("Test, CRUD! endpoint was called");
        return "$$Test, CRUD ##01!";
    }

    @GetMapping("/random-check")
    public ResponseEntity<String> getRandomNumberResponse() throws InterruptedException {
        int num = random.nextInt(10) + 1; // 1–10

        // Determine latency based on divisibility
        long latencyMillis = 0;

        if (num % 2 == 0) {
            latencyMillis = 5000 + random.nextInt(200); // 100–300 ms
        }
        if (num % 3 == 0) {
            latencyMillis = 7000 + random.nextInt(300); // 200–500 ms
        }
        if (num % 4 == 0) {
            latencyMillis = 1500 + random.nextInt(250); // 150–400 ms
        }
        if (num % 5 == 0) {
            latencyMillis = 2300 + random.nextInt(300); // 300–600 ms
        }
        if (num % 7 == 0) {
            latencyMillis = 1400 + random.nextInt(400); // 400–800 ms
        }

        // Sleep to simulate latency
        if (latencyMillis > 0) {
            Thread.sleep(latencyMillis);
        }

        // Return HTTP status based on divisibility
        if (num % 2 == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Random number: " + num + " → Divisible by 2 → Returning 404 with latency " + latencyMillis + "ms");
        } else if (num % 3 == 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Random number: " + num + " → Divisible by 3 → Returning 500 with latency " + latencyMillis + "ms");
        } else {
            return ResponseEntity.ok("Random number: " + num + " → Success with latency " + latencyMillis + "ms");
        }
    }


/**
 *
 Properties:
 Property:
 - name: serviceName
 value: logging-monitoring-service-1
 - name: logDir
 value: /Users/rahulsoni/logs/${serviceName}
 - name: logFile
 value: ${logDir}/app.log
 */

}
