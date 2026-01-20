package com.weather.station.service;

import com.weather.station.config.WeatherConfig;
import com.weather.station.model.enums.WeatherCondition;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Service responsible for generating randomized weather data.
 * <p>
 * This generator produces mock weather data for testing and prototyping purposes.
 * All data is randomly generated within realistic ranges defined in {@link WeatherConfig}.
 * Thread-safe implementation using {@link ThreadLocalRandom}.
 * </p>
 *
 * <h3>Data Ranges (configurable in application.properties):</h3>
 * <ul>
 *   <li>Temperature: -15.0°C to 35.0°C (1 decimal place)</li>
 *   <li>Humidity: 20% to 100%</li>
 *   <li>Wind Speed: 0.0 km/h to 50.0 km/h (1 decimal place)</li>
 *   <li>Precipitation: 0% to 100%</li>
 * </ul>
 *
 * @since 1.0.0
 */
@Service
public class WeatherDataGenerator {

    private final WeatherConfig config;

    public WeatherDataGenerator(WeatherConfig config) {
        this.config = config;
    }

    /**
     * Generates a random temperature within the configured range.
     *
     * @return temperature value with 1 decimal place precision
     */
    public double generateTemperature() {
        double temp = ThreadLocalRandom.current()
                .nextDouble(config.getTemperature().getMin(), config.getTemperature().getMax());
        double roundingFactor = Math.pow(10, config.getFormat().getDecimalPlaces());
        return Math.round(temp * roundingFactor) / roundingFactor;
    }

    /**
     * Generates a random humidity percentage within the configured range.
     *
     * @return humidity value between configured min and max (inclusive)
     */
    public int generateHumidity() {
        return ThreadLocalRandom.current()
                .nextInt(config.getHumidity().getMin(), config.getHumidity().getMax() + 1);
    }

    /**
     * Generates a random wind speed within the configured range.
     *
     * @return wind speed value with 1 decimal place precision in km/h
     */
    public double generateWindSpeed() {
        double speed = ThreadLocalRandom.current()
                .nextDouble(config.getWindSpeed().getMin(), config.getWindSpeed().getMax());
        double roundingFactor = Math.pow(10, config.getFormat().getDecimalPlaces());
        return Math.round(speed * roundingFactor) / roundingFactor;
    }

    /**
     * Generates a random weather condition from the available enum values.
     *
     * @return randomly selected weather condition
     */
    public WeatherCondition generateCondition() {
        WeatherCondition[] conditions = WeatherCondition.values();
        int index = ThreadLocalRandom.current().nextInt(conditions.length);
        return conditions[index];
    }

    /**
     * Generates a random minimum temperature for forecast data.
     *
     * @return minimum temperature with 1 decimal place precision
     */
    public double generateTempMin() {
        double temp = ThreadLocalRandom.current()
                .nextDouble(config.getTemperature().getForecastMin(),
                           config.getTemperature().getForecastMax());
        double roundingFactor = Math.pow(10, config.getFormat().getDecimalPlaces());
        return Math.round(temp * roundingFactor) / roundingFactor;
    }

    /**
     * Generates a maximum temperature value that is guaranteed to be
     * greater than or equal to the provided minimum temperature.
     * <p>
     * This method enforces the constraint: tempMax >= tempMin
     * </p>
     *
     * @param tempMin the minimum temperature
     * @return a random maximum temperature between tempMin and configured max
     */
    public double generateTempMax(double tempMin) {
        double temp = ThreadLocalRandom.current()
                .nextDouble(tempMin, config.getTemperature().getMax());
        double roundingFactor = Math.pow(10, config.getFormat().getDecimalPlaces());
        return Math.round(temp * roundingFactor) / roundingFactor;
    }

    /**
     * Generates a random precipitation percentage.
     *
     * @return precipitation value between configured min and max (inclusive)
     */
    public int generatePrecipitation() {
        return ThreadLocalRandom.current()
                .nextInt(config.getPrecipitation().getMin(),
                        config.getPrecipitation().getMax() + 1);
    }

    /**
     * Generates the current timestamp in ISO 8601 format.
     *
     * @return formatted timestamp string (e.g., "2026-01-20T12:00:00")
     */
    public String generateCurrentTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(config.getFormat().getTimestamp());
        return now.format(formatter);
    }

    /**
     * Generates a list of forecast dates starting from tomorrow.
     * <p>
     * The number of days generated is determined by the configuration.
     * </p>
     *
     * @return list of date strings in ISO format (e.g., "2026-01-21")
     */
    public List<String> generateForecastDates() {
        List<String> dates = new ArrayList<>();
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(config.getFormat().getDate());

        for (int i = 0; i < config.getForecast().getDays(); i++) {
            dates.add(tomorrow.plusDays(i).format(formatter));
        }

        return dates;
    }
}
