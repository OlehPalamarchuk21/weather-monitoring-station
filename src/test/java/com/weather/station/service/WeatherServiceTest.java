package com.weather.station.service;

import com.weather.station.config.WeatherConfig;
import com.weather.station.model.dto.CurrentWeatherResponse;
import com.weather.station.model.dto.ForecastResponse;
import com.weather.station.model.enums.WeatherCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    @Mock
    private WeatherDataGenerator dataGenerator;

    @Mock
    private WeatherConfig weatherConfig;

    @InjectMocks
    private WeatherService weatherService;

    @BeforeEach
    void setUp() {
        WeatherConfig.CityConfig cityConfig = new WeatherConfig.CityConfig();
        cityConfig.setName("Windholm");
        when(weatherConfig.getCity()).thenReturn(cityConfig);
    }

    @Test
    void shouldGetCurrentWeatherWithWindholmAsCity() {
        when(dataGenerator.generateCurrentTimestamp()).thenReturn("2025-01-14T15:32:00");
        when(dataGenerator.generateTemperature()).thenReturn(22.5);
        when(dataGenerator.generateHumidity()).thenReturn(65);
        when(dataGenerator.generateWindSpeed()).thenReturn(15.3);
        when(dataGenerator.generateCondition()).thenReturn(WeatherCondition.SUNNY);

        CurrentWeatherResponse response = weatherService.getCurrentWeather();

        assertNotNull(response);
        assertEquals("Windholm", response.getCity());
    }

    @Test
    void shouldGetCurrentWeatherWithAllFieldsPopulated() {
        when(dataGenerator.generateCurrentTimestamp()).thenReturn("2025-01-14T15:32:00");
        when(dataGenerator.generateTemperature()).thenReturn(22.5);
        when(dataGenerator.generateHumidity()).thenReturn(65);
        when(dataGenerator.generateWindSpeed()).thenReturn(15.3);
        when(dataGenerator.generateCondition()).thenReturn(WeatherCondition.SUNNY);

        CurrentWeatherResponse response = weatherService.getCurrentWeather();

        assertNotNull(response);
        assertEquals("Windholm", response.getCity());
        assertEquals("2025-01-14T15:32:00", response.getTimestamp());
        assertEquals(22.5, response.getTemperature());
        assertEquals(65, response.getHumidity());
        assertEquals(15.3, response.getWindSpeed());
        assertEquals(WeatherCondition.SUNNY, response.getCondition());
    }

    @Test
    void shouldCallDataGeneratorMethods() {
        when(dataGenerator.generateCurrentTimestamp()).thenReturn("2025-01-14T15:32:00");
        when(dataGenerator.generateTemperature()).thenReturn(22.5);
        when(dataGenerator.generateHumidity()).thenReturn(65);
        when(dataGenerator.generateWindSpeed()).thenReturn(15.3);
        when(dataGenerator.generateCondition()).thenReturn(WeatherCondition.SUNNY);

        weatherService.getCurrentWeather();

        verify(dataGenerator).generateCurrentTimestamp();
        verify(dataGenerator).generateTemperature();
        verify(dataGenerator).generateHumidity();
        verify(dataGenerator).generateWindSpeed();
        verify(dataGenerator).generateCondition();
    }

    @Test
    void shouldGetForecastWithWindholmAsCity() {
        List<String> forecastDates = Arrays.asList(
                "2025-01-15", "2025-01-16", "2025-01-17",
                "2025-01-18", "2025-01-19", "2025-01-20", "2025-01-21"
        );
        when(dataGenerator.generateForecastDates()).thenReturn(forecastDates);
        when(dataGenerator.generateTempMin()).thenReturn(10.0);
        when(dataGenerator.generateTempMax(anyDouble())).thenReturn(25.0);
        when(dataGenerator.generateCondition()).thenReturn(WeatherCondition.SUNNY);
        when(dataGenerator.generatePrecipitation()).thenReturn(20);
        when(dataGenerator.generateCurrentTimestamp()).thenReturn("2025-01-14T15:32:00");

        ForecastResponse response = weatherService.getForecast();

        assertNotNull(response);
        assertEquals("Windholm", response.getCity());
    }

    @Test
    void shouldGetForecastWithSevenDays() {
        List<String> forecastDates = Arrays.asList(
                "2025-01-15", "2025-01-16", "2025-01-17",
                "2025-01-18", "2025-01-19", "2025-01-20", "2025-01-21"
        );
        when(dataGenerator.generateForecastDates()).thenReturn(forecastDates);
        when(dataGenerator.generateTempMin()).thenReturn(10.0);
        when(dataGenerator.generateTempMax(anyDouble())).thenReturn(25.0);
        when(dataGenerator.generateCondition()).thenReturn(WeatherCondition.SUNNY);
        when(dataGenerator.generatePrecipitation()).thenReturn(20);
        when(dataGenerator.generateCurrentTimestamp()).thenReturn("2025-01-14T15:32:00");

        ForecastResponse response = weatherService.getForecast();

        assertNotNull(response);
        assertEquals(7, response.getForecast().size());
    }

    @Test
    void shouldGetForecastWithAllFieldsPopulated() {
        List<String> forecastDates = Arrays.asList(
                "2025-01-15", "2025-01-16", "2025-01-17",
                "2025-01-18", "2025-01-19", "2025-01-20", "2025-01-21"
        );
        when(dataGenerator.generateForecastDates()).thenReturn(forecastDates);
        when(dataGenerator.generateTempMin()).thenReturn(10.0);
        when(dataGenerator.generateTempMax(anyDouble())).thenReturn(25.0);
        when(dataGenerator.generateCondition()).thenReturn(WeatherCondition.SUNNY);
        when(dataGenerator.generatePrecipitation()).thenReturn(20);
        when(dataGenerator.generateCurrentTimestamp()).thenReturn("2025-01-14T15:32:00");

        ForecastResponse response = weatherService.getForecast();

        assertNotNull(response);
        assertEquals("Windholm", response.getCity());
        assertEquals("2025-01-14T15:32:00", response.getGeneratedAt());
        assertEquals(7, response.getForecast().size());

        for (int i = 0; i < 7; i++) {
            assertNotNull(response.getForecast().get(i));
            assertEquals(forecastDates.get(i), response.getForecast().get(i).getDate());
            assertNotNull(response.getForecast().get(i).getTempMin());
            assertNotNull(response.getForecast().get(i).getTempMax());
            assertNotNull(response.getForecast().get(i).getCondition());
            assertNotNull(response.getForecast().get(i).getPrecipitation());
        }
    }
}
