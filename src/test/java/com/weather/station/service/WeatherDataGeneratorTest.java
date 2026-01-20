package com.weather.station.service;

import com.weather.station.config.WeatherConfig;
import com.weather.station.model.enums.WeatherCondition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WeatherDataGeneratorTest {

    @Autowired
    private WeatherDataGenerator generator;

    @Autowired
    private WeatherConfig config;

    @Test
    void shouldGenerateTemperatureInValidRange() {
        for (int i = 0; i < 100; i++) {
            double temperature = generator.generateTemperature();
            assertTrue(temperature >= -15.0, "Temperature should be >= -15.0, but was: " + temperature);
            assertTrue(temperature <= 35.0, "Temperature should be <= 35.0, but was: " + temperature);
        }
    }

    @Test
    void shouldGenerateTemperatureWithOneDecimal() {
        for (int i = 0; i < 100; i++) {
            double temperature = generator.generateTemperature();
            String tempStr = String.valueOf(temperature);
            int decimalIndex = tempStr.indexOf('.');
            if (decimalIndex != -1) {
                int decimalPlaces = tempStr.length() - decimalIndex - 1;
                assertTrue(decimalPlaces <= 1, "Temperature should have at most 1 decimal place, but was: " + temperature);
            }
        }
    }

    @Test
    void shouldGenerateHumidityInValidRange() {
        for (int i = 0; i < 100; i++) {
            int humidity = generator.generateHumidity();
            assertTrue(humidity >= 20, "Humidity should be >= 20, but was: " + humidity);
            assertTrue(humidity <= 100, "Humidity should be <= 100, but was: " + humidity);
        }
    }

    @Test
    void shouldGenerateWindSpeedInValidRange() {
        for (int i = 0; i < 100; i++) {
            double windSpeed = generator.generateWindSpeed();
            assertTrue(windSpeed >= 0.0, "Wind speed should be >= 0.0, but was: " + windSpeed);
            assertTrue(windSpeed <= 50.0, "Wind speed should be <= 50.0, but was: " + windSpeed);
        }
    }

    @Test
    void shouldGenerateWindSpeedWithOneDecimal() {
        for (int i = 0; i < 100; i++) {
            double windSpeed = generator.generateWindSpeed();
            String windStr = String.valueOf(windSpeed);
            int decimalIndex = windStr.indexOf('.');
            if (decimalIndex != -1) {
                int decimalPlaces = windStr.length() - decimalIndex - 1;
                assertTrue(decimalPlaces <= 1, "Wind speed should have at most 1 decimal place, but was: " + windSpeed);
            }
        }
    }

    @Test
    void shouldGenerateRandomWeatherCondition() {
        Set<WeatherCondition> conditions = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            WeatherCondition condition = generator.generateCondition();
            assertNotNull(condition);
            conditions.add(condition);
        }
        assertTrue(conditions.size() > 1, "Should generate variety of conditions");
    }

    @Test
    void shouldGenerateTempMinInValidRange() {
        for (int i = 0; i < 100; i++) {
            double tempMin = generator.generateTempMin();
            assertTrue(tempMin >= -15.0, "TempMin should be >= -15.0, but was: " + tempMin);
            assertTrue(tempMin <= 20.0, "TempMin should be <= 20.0, but was: " + tempMin);
        }
    }

    @Test
    void shouldGenerateTempMinWithOneDecimal() {
        for (int i = 0; i < 100; i++) {
            double tempMin = generator.generateTempMin();
            String tempStr = String.valueOf(tempMin);
            int decimalIndex = tempStr.indexOf('.');
            if (decimalIndex != -1) {
                int decimalPlaces = tempStr.length() - decimalIndex - 1;
                assertTrue(decimalPlaces <= 1, "TempMin should have at most 1 decimal place, but was: " + tempMin);
            }
        }
    }

    @Test
    void shouldGenerateTempMaxGreaterThanOrEqualToTempMin() {
        for (int i = 0; i < 100; i++) {
            double tempMin = generator.generateTempMin();
            double tempMax = generator.generateTempMax(tempMin);
            assertTrue(tempMax >= tempMin, "TempMax (" + tempMax + ") should be >= TempMin (" + tempMin + ")");
            assertTrue(tempMax <= 35.0, "TempMax should be <= 35.0, but was: " + tempMax);
        }
    }

    @Test
    void shouldGenerateTempMaxWithOneDecimal() {
        for (int i = 0; i < 100; i++) {
            double tempMin = generator.generateTempMin();
            double tempMax = generator.generateTempMax(tempMin);
            String tempStr = String.valueOf(tempMax);
            int decimalIndex = tempStr.indexOf('.');
            if (decimalIndex != -1) {
                int decimalPlaces = tempStr.length() - decimalIndex - 1;
                assertTrue(decimalPlaces <= 1, "TempMax should have at most 1 decimal place, but was: " + tempMax);
            }
        }
    }

    @Test
    void shouldGeneratePrecipitationInValidRange() {
        for (int i = 0; i < 100; i++) {
            int precipitation = generator.generatePrecipitation();
            assertTrue(precipitation >= 0, "Precipitation should be >= 0, but was: " + precipitation);
            assertTrue(precipitation <= 100, "Precipitation should be <= 100, but was: " + precipitation);
        }
    }

    @Test
    void shouldGenerateCurrentTimestamp() {
        String timestamp = generator.generateCurrentTimestamp();
        assertNotNull(timestamp);
        assertTrue(timestamp.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}"),
                "Timestamp should match ISO 8601 format: " + timestamp);
    }

    @Test
    void shouldGenerateSevenDayForecastDates() {
        List<String> dates = generator.generateForecastDates();
        assertNotNull(dates);
        assertEquals(7, dates.size(), "Should generate exactly 7 forecast dates");

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

        for (int i = 0; i < 7; i++) {
            String expectedDate = tomorrow.plusDays(i).format(formatter);
            assertEquals(expectedDate, dates.get(i), "Date at index " + i + " should be " + expectedDate);
        }
    }

    @Test
    void shouldGenerateForecastDatesInCorrectFormat() {
        List<String> dates = generator.generateForecastDates();
        for (String date : dates) {
            assertTrue(date.matches("\\d{4}-\\d{2}-\\d{2}"),
                    "Date should match YYYY-MM-DD format: " + date);
        }
    }
}
