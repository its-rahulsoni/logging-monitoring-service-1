package com.logging.monitoring.learning.services;

import com.logging.monitoring.learning.predicates.PendingRetryPredicate;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RetryWrapperService {

    private final MyService myService;
    private final PendingRetryPredicate predicate;

    public RetryWrapperService(MyService myService, PendingRetryPredicate predicate) {
        this.myService = myService;
        this.predicate = predicate;
    }

    public String callWithRetry() {

        RetryConfig config = RetryConfig.<String>custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofSeconds(1))
                .retryOnResult(predicate)
                .build();

        Retry retry = Retry.of("spring-retry", config);

        return Retry.decorateSupplier(retry, () -> myService.checkStatus())
                .get();
    }
}
