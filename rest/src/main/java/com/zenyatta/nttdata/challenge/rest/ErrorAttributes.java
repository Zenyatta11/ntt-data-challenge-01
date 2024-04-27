package com.zenyatta.nttdata.challenge.rest;

import static java.time.LocalDateTime.now;
import static java.time.ZoneOffset.UTC;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;

public interface ErrorAttributes {
    static Map<String, Object> getErrorAttributes(
            final HttpStatus httpStatus, 
            final String message,
            final WebRequest request
    ) {
        final Map<String, Object> errorAttributes = new HashMap<>();
        errorAttributes.put("timestamp", now(UTC));
        errorAttributes.put("status", httpStatus.value());
        errorAttributes.put("error", httpStatus.getReasonPhrase());
        errorAttributes.put("message", message);

        final String requestURI = ((ServletRequestAttributes) request).getRequest().getRequestURI();
        errorAttributes.put("path", requestURI);

        return errorAttributes;
    }
}
