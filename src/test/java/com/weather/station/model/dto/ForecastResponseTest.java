package com.weather.station.model.dto;

import com.weather.station.model.enums.WeatherCondition;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ForecastResponseTest {

    @Test
    void shouldCreateWithBuilder() {
        List<ForecastDay> forecast = createSevenDayForecast();

        ForecastResponse response = ForecastResponse.builder()
                .city("Windholm")
                .generatedAt("2025-01-14T15:32:00")
                .forecast(forecast)
                .build();

        assertNotNull(response);
        assertEquals("Windholm", response.getCity());
        assertEquals("2025-01-14T15:32:00", response.getGeneratedAt());
        assertEquals(7, response.getForecast().size());
    }

    @Test
    void shouldCreateWithNoArgsConstructor() {
        ForecastResponse response = new ForecastResponse();
        assertNotNull(response);
    }

    @Test
    void shouldCreateWithAllArgsConstructor() {
        List<ForecastDay> forecast = createSevenDayForecast();

        ForecastResponse response = new ForecastResponse(
                "Windholm",
                "2025-01-14T15:32:00",
                forecast
        );

        assertNotNull(response);
        assertEquals("Windholm", response.getCity());
        assertEquals("2025-01-14T15:32:00", response.getGeneratedAt());
        assertEquals(7, response.getForecast().size());
    }

    @Test
    void shouldHandleSevenDayForecast() {
        List<ForecastDay> forecast = createSevenDayForecast();

        ForecastResponse response = ForecastResponse.builder()
                .city("Windholm")
                .generatedAt("2025-01-14T15:32:00")
                .forecast(forecast)
                .build();

        assertEquals(7, response.getForecast().size());

        for (int i = 0; i < 7; i++) {
            assertNotNull(response.getForecast().get(i));
            assertNotNull(response.getForecast().get(i).getDate());
        }
    }

    @Test
    void shouldAllowSettersAndGetters() {
        List<ForecastDay> forecast = createSevenDayForecast();

        ForecastResponse response = new ForecastResponse();
        response.setCity("Windholm");
        response.setGeneratedAt("2025-01-14T15:32:00");
        response.setForecast(forecast);

        assertEquals("Windholm", response.getCity());
        assertEquals("2025-01-14T15:32:00", response.getGeneratedAt());
        assertEquals(7, response.getForecast().size());
    }

    @Test
    void shouldHandleEmptyForecastList() {
        ForecastResponse response = ForecastResponse.builder()
                .city("Windholm")
                .generatedAt("2025-01-14T15:32:00")
                .forecast(new ArrayList<>())
                .build();

        assertNotNull(response.getForecast());
        assertEquals(0, response.getForecast().size());
    }

    private List<ForecastDay> createSevenDayForecast() {
        List<ForecastDay> forecast = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            forecast.add(ForecastDay.builder()
                    .date("2025-01-" + (15 + i))
                    .tempMin(10.0 + i)
                    .tempMax(20.0 + i)
                    .condition(WeatherCondition.SUNNY)
                    .precipitation(20)
                    .build());
        }
        return forecast;
    }
}
