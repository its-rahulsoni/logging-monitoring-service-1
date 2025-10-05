package com.logging.monitoring.learning.filters;

import jakarta.servlet.*;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.SecureRandom;

/**
 * By annotating the filter class with @Component, Spring Boot automatically detects and registers it as a bean in the application context,
 * making it part of the filter chain.
 */
@Component
public class ServiceNameFilter implements Filter {

    private static final SecureRandom random = new SecureRandom();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String requestId = generateRequestId();
        ThreadContext.put("requestId", requestId); // set MDC

        try {
            chain.doFilter(request, response);
        } finally {
            // Clean up the ThreadContext to prevent leaking
            ThreadContext.clearMap(); // Clears all keys in the MDC
        }
    }

    private String generateRequestId() {
        // 10-digit unique ID
        long number = Math.abs(random.nextLong()) % 1_000_000_0000L;
        return String.format("%010d", number);
    }
}
