package com.weather.station.model.dto;

import com.weather.station.model.enums.WeatherCondition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrentWeatherResponseTest {

    @Test
    void shouldCreateWithBuilder() {
        CurrentWeatherResponse response = CurrentWeatherResponse.builder()
                .city("Windholm")
                .timestamp("2025-01-14T15:32:00")
                .temperature(22.5)
                .humidity(65)
                .windSpeed(15.3)
                .condition(WeatherCondition.SUNNY)
                .build();

        assertNotNull(response);
        assertEquals("Windholm", response.getCity());
        assertEquals("2025-01-14T15:32:00", response.getTimestamp());
        assertEquals(22.5, response.getTemperature());
        assertEquals(65, response.getHumidity());
        assertEquals(15.3, response.getWindSpeed());
        assertEquals(WeatherCondition.SUNNY, response.getCondition());
    }

    @Test
    void shouldCreateWithNoArgsConstructor() {
        CurrentWeatherResponse response = new CurrentWeatherResponse();
        assertNotNull(response);
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        CurrentWeatherResponse response = new CurrentWeatherResponse(
                "Windholm",
                "2025-01-14T15:32:00",
                22.5,
                65,
                15.3,
                WeatherCondition.SUNNY
        );

        assertNotNull(response);
        assertEquals("Windholm", response.getCity());
        assertEquals("2025-01-14T15:32:00", response.getTimestamp());
        assertEquals(22.5, response.getTemperature());
        assertEquals(65, response.getHumidity());
        assertEquals(15.3, response.getWindSpeed());
        assertEquals(WeatherCondition.SUNNY, response.getCondition());
    }

    @Test
    void shouldHandleAllWeatherConditions() {
        for (WeatherCondition condition : WeatherCondition.values()) {
            CurrentWeatherResponse response = CurrentWeatherResponse.builder()
                    .city("Windholm")
                    .timestamp("2025-01-14T15:32:00")
                    .temperature(20.0)
                    .humidity(50)
                    .windSpeed(10.0)
                    .condition(condition)
                    .build();

            assertEquals(condition, response.getCondition());
        }
    }

    @Test
    void shouldHandleOneDecimalTemperature() {
        CurrentWeatherResponse response = CurrentWeatherResponse.builder()
                .city("Windholm")
                .timestamp("2025-01-14T15:32:00")
                .temperature(22.5)
                .humidity(65)
                .windSpeed(15.3)
                .condition(WeatherCondition.SUNNY)
                .build();

        assertEquals(22.5, response.getTemperature());
    }

    @Test
    void shouldHandleOneDecimalWindSpeed() {
        CurrentWeatherResponse response = CurrentWeatherResponse.builder()
                .city("Windholm")
                .timestamp("2025-01-14T15:32:00")
                .temperature(22.5)
                .humidity(65)
                .windSpeed(15.3)
                .condition(WeatherCondition.SUNNY)
                .build();

        assertEquals(15.3, response.getWindSpeed());
    }

    @Test
    void shouldAllowSettersAndGetters() {
        CurrentWeatherResponse response = new CurrentWeatherResponse();

        response.setCity("Windholm");
        response.setTimestamp("2025-01-14T15:32:00");
        response.setTemperature(22.5);
        response.setHumidity(65);
        response.setWindSpeed(15.3);
        response.setCondition(WeatherCondition.SUNNY);

        assertEquals("Windholm", response.getCity());
        assertEquals("2025-01-14T15:32:00", response.getTimestamp());
        assertEquals(22.5, response.getTemperature());
        assertEquals(65, response.getHumidity());
        assertEquals(15.3, response.getWindSpeed());
        assertEquals(WeatherCondition.SUNNY, response.getCondition());
    }
}
