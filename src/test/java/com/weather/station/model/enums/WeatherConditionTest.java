package com.weather.station.model.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeatherConditionTest {

    @Test
    void shouldHaveSevenValues() {
        assertEquals(7, WeatherCondition.values().length);
    }

    @Test
    void shouldHaveCorrectEnumValues() {
        assertNotNull(WeatherCondition.valueOf("SUNNY"));
        assertNotNull(WeatherCondition.valueOf("CLOUDY"));
        assertNotNull(WeatherCondition.valueOf("RAINY"));
        assertNotNull(WeatherCondition.valueOf("STORMY"));
        assertNotNull(WeatherCondition.valueOf("SNOWY"));
        assertNotNull(WeatherCondition.valueOf("FOGGY"));
        assertNotNull(WeatherCondition.valueOf("WINDY"));
    }

    @Test
    void shouldHaveAllExpectedValues() {
        WeatherCondition[] conditions = WeatherCondition.values();

        assertTrue(containsValue(conditions, "SUNNY"));
        assertTrue(containsValue(conditions, "CLOUDY"));
        assertTrue(containsValue(conditions, "RAINY"));
        assertTrue(containsValue(conditions, "STORMY"));
        assertTrue(containsValue(conditions, "SNOWY"));
        assertTrue(containsValue(conditions, "FOGGY"));
        assertTrue(containsValue(conditions, "WINDY"));
    }

    private boolean containsValue(WeatherCondition[] conditions, String name) {
        for (WeatherCondition condition : conditions) {
            if (condition.name().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
