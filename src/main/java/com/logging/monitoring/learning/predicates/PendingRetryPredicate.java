package com.logging.monitoring.learning.predicates;

import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class PendingRetryPredicate implements Predicate<String> {

    public PendingRetryPredicate() {
        System.out.println("PREDICATE CREATED");
    }

    @Override
    public boolean test(String result) {
        System.out.println("Predicate checking: " + result);
        return result.toString().contains("PENDING");
    }

}
