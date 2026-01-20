package com.weather.station.exception;

import com.weather.station.model.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;

/**
 * Global exception handler for all REST controllers.
 * <p>
 * Provides centralized exception handling and consistent error responses
 * across the entire application. Prevents stack trace leakage to clients
 * and ensures proper logging of all errors.
 * </p>
 *
 * @since 1.0.0
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Handles all generic uncaught exceptions.
     *
     * @param ex the exception that occurred
     * @param request the HTTP request that caused the exception
     * @return standardized error response
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGenericException(Exception ex, HttpServletRequest request) {
        log.error("Unexpected error occurred while processing request to {}", request.getRequestURI(), ex);

        return ErrorResponse.builder()
                .timestamp(Instant.now().toString())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message("An unexpected error occurred while processing your request")
                .path(request.getRequestURI())
                .build();
    }

    /**
     * Handles illegal argument exceptions (validation failures).
     *
     * @param ex the illegal argument exception
     * @param request the HTTP request that caused the exception
     * @return standardized error response with validation message
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest request) {
        log.warn("Invalid argument provided to {}: {}", request.getRequestURI(), ex.getMessage());

        return ErrorResponse.builder()
                .timestamp(Instant.now().toString())
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    /**
     * Handles null pointer exceptions.
     *
     * @param ex the null pointer exception
     * @param request the HTTP request that caused the exception
     * @return standardized error response
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleNullPointer(NullPointerException ex, HttpServletRequest request) {
        log.error("Null pointer exception occurred while processing request to {}", request.getRequestURI(), ex);

        return ErrorResponse.builder()
                .timestamp(Instant.now().toString())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .message("An internal error occurred due to missing data")
                .path(request.getRequestURI())
                .build();
    }
}
