package com.logging.monitoring.learning.controllers;

import com.logging.monitoring.learning.services.MyService;
import com.logging.monitoring.learning.services.RetryWrapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RetryController {

    @Autowired
    private final MyService myService;

    @Autowired
    RetryWrapperService retryWrapperService;

    public RetryController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/retry-test")
    public String testRetry() {
           return retryWrapperService.callWithRetry();
    }
}