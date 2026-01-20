package com.weather.station.model.dto;

import com.weather.station.model.enums.WeatherCondition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ForecastDayTest {

    @Test
    void shouldCreateWithBuilder() {
        ForecastDay forecastDay = ForecastDay.builder()
                .date("2025-01-15")
                .tempMin(10.5)
                .tempMax(25.3)
                .condition(WeatherCondition.SUNNY)
                .precipitation(20)
                .build();

        assertNotNull(forecastDay);
        assertEquals("2025-01-15", forecastDay.getDate());
        assertEquals(10.5, forecastDay.getTempMin());
        assertEquals(25.3, forecastDay.getTempMax());
        assertEquals(WeatherCondition.SUNNY, forecastDay.getCondition());
        assertEquals(20, forecastDay.getPrecipitation());
    }

    @Test
    void shouldCreateWithNoArgsConstructor() {
        ForecastDay forecastDay = new ForecastDay();
        assertNotNull(forecastDay);
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        ForecastDay forecastDay = new ForecastDay(
                "2025-01-15",
                10.5,
                25.3,
                WeatherCondition.SUNNY,
                20
        );

        assertNotNull(forecastDay);
        assertEquals("2025-01-15", forecastDay.getDate());
        assertEquals(10.5, forecastDay.getTempMin());
        assertEquals(25.3, forecastDay.getTempMax());
        assertEquals(WeatherCondition.SUNNY, forecastDay.getCondition());
        assertEquals(20, forecastDay.getPrecipitation());
    }

    @Test
    void shouldHandleDateFormat() {
        ForecastDay forecastDay = ForecastDay.builder()
                .date("2025-01-15")
                .tempMin(10.0)
                .tempMax(20.0)
                .condition(WeatherCondition.CLOUDY)
                .precipitation(30)
                .build();

        assertEquals("2025-01-15", forecastDay.getDate());
        assertTrue(forecastDay.getDate().matches("\\d{4}-\\d{2}-\\d{2}"));
    }

    @Test
    void shouldHandleAllWeatherConditions() {
        for (WeatherCondition condition : WeatherCondition.values()) {
            ForecastDay forecastDay = ForecastDay.builder()
                    .date("2025-01-15")
                    .tempMin(10.0)
                    .tempMax(20.0)
                    .condition(condition)
                    .precipitation(30)
                    .build();

            assertEquals(condition, forecastDay.getCondition());
        }
    }

    @Test
    void shouldHandleOneDecimalTemperatures() {
        ForecastDay forecastDay = ForecastDay.builder()
                .date("2025-01-15")
                .tempMin(10.5)
                .tempMax(25.3)
                .condition(WeatherCondition.SUNNY)
                .precipitation(20)
                .build();

        assertEquals(10.5, forecastDay.getTempMin());
        assertEquals(25.3, forecastDay.getTempMax());
    }

    @Test
    void shouldAllowSettersAndGetters() {
        ForecastDay forecastDay = new ForecastDay();

        forecastDay.setDate("2025-01-15");
        forecastDay.setTempMin(10.5);
        forecastDay.setTempMax(25.3);
        forecastDay.setCondition(WeatherCondition.SUNNY);
        forecastDay.setPrecipitation(20);

        assertEquals("2025-01-15", forecastDay.getDate());
        assertEquals(10.5, forecastDay.getTempMin());
        assertEquals(25.3, forecastDay.getTempMax());
        assertEquals(WeatherCondition.SUNNY, forecastDay.getCondition());
        assertEquals(20, forecastDay.getPrecipitation());
    }
}
