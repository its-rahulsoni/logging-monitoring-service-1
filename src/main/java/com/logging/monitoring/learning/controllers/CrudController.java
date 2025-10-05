package com.logging.monitoring.learning.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/crud")
public class CrudController {

    private static final Logger logger = LogManager.getLogger(CrudController.class);

    @GetMapping("/hello")
    public String hello() {
        System.out.println("Current MDC: " + ThreadContext.getImmutableContext());
        logger.info("Hello, CRUD! endpoint was called");
        logger.debug("DEBUG, CRUD! endpoint was called");
        logger.error("ERROR, CRUD! endpoint was called");
        logger.warn("WARN, CRUD! endpoint was called");
        logger.info("Hello, CRUD! endpoint was called");
        return "Hello, CRUD!";
    }

    @GetMapping("/test")
    public String test() {
        logger.info("Test, CRUD! endpoint was called");
        return "Test, CRUD!";
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
