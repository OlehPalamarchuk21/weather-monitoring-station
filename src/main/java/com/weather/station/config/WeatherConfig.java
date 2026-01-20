package com.weather.station.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration properties for weather data generation.
 * <p>
 * This class binds to properties prefixed with "weather" in application.properties,
 * providing type-safe access to configuration values used throughout the application.
 * </p>
 *
 * @since 1.0.0
 */
@Configuration
@ConfigurationProperties(prefix = "weather")
@Data
public class WeatherConfig {

    private CityConfig city = new CityConfig();
    private TemperatureConfig temperature = new TemperatureConfig();
    private HumidityConfig humidity = new HumidityConfig();
    private WindSpeedConfig windSpeed = new WindSpeedConfig();
    private PrecipitationConfig precipitation = new PrecipitationConfig();
    private ForecastConfig forecast = new ForecastConfig();
    private FormatConfig format = new FormatConfig();

    /**
     * City-related configuration properties.
     */
    @Data
    public static class CityConfig {
        private String name;
        private String country;
        private String timezone;
    }

    /**
     * Temperature range configuration properties.
     */
    @Data
    public static class TemperatureConfig {
        private double min;
        private double max;
        private double forecastMin;
        private double forecastMax;
    }

    /**
     * Humidity range configuration properties.
     */
    @Data
    public static class HumidityConfig {
        private int min;
        private int max;
    }

    /**
     * Wind speed range configuration properties.
     */
    @Data
    public static class WindSpeedConfig {
        private double min;
        private double max;
    }

    /**
     * Precipitation range configuration properties.
     */
    @Data
    public static class PrecipitationConfig {
        private int min;
        private int max;
    }

    /**
     * Forecast-related configuration properties.
     */
    @Data
    public static class ForecastConfig {
        private int days;
    }

    /**
     * Date and time formatting configuration properties.
     */
    @Data
    public static class FormatConfig {
        private String timestamp;
        private String date;
        private int decimalPlaces;
    }
}
