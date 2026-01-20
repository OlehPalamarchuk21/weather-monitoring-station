package com.weather.station.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for API error responses.
 * <p>
 * Provides a consistent error response format across all API endpoints.
 * </p>
 *
 * @since 1.0.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    /**
     * ISO 8601 timestamp when the error occurred.
     */
    private String timestamp;

    /**
     * HTTP status code.
     */
    private int status;

    /**
     * Brief error type or category.
     */
    private String error;

    /**
     * Detailed error message for the client.
     */
    private String message;

    /**
     * API path where the error occurred.
     */
    private String path;
}
