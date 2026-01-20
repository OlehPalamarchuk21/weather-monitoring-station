package com.weather.station.controller;

import com.weather.station.exception.GlobalExceptionHandler;
import com.weather.station.model.dto.CurrentWeatherResponse;
import com.weather.station.model.dto.ForecastDay;
import com.weather.station.model.dto.ForecastResponse;
import com.weather.station.model.enums.WeatherCondition;
import com.weather.station.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({WeatherController.class, GlobalExceptionHandler.class})
class WeatherControllerTest {

    @MockitoBean
    private WeatherService weatherService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .alwaysDo(print())
                .build();
    }

    @Test
    void shouldGetCurrentWeather() throws Exception {
        CurrentWeatherResponse mockResponse = buildCurrentWeatherResponse();
        when(weatherService.getCurrentWeather()).thenReturn(mockResponse);

        ResultActions result = mockMvc.perform(get("/api/weather/current"));

        result.andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.city").value("Windholm"))
                .andExpect(jsonPath("$.timestamp").value("2025-01-14T15:32:00"))
                .andExpect(jsonPath("$.temperature").value(22.5))
                .andExpect(jsonPath("$.humidity").value(65))
                .andExpect(jsonPath("$.windSpeed").value(15.3))
                .andExpect(jsonPath("$.condition").value("SUNNY"));
    }

    @Test
    void shouldGetForecast() throws Exception {
        ForecastResponse mockResponse = buildForecastResponse();
        when(weatherService.getForecast()).thenReturn(mockResponse);

        ResultActions result = mockMvc.perform(get("/api/weather/forecast"));

        result.andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.city").value("Windholm"))
                .andExpect(jsonPath("$.generatedAt").value("2025-01-14T15:32:00"))
                .andExpect(jsonPath("$.forecast").isArray())
                .andExpect(jsonPath("$.forecast.length()").value(2))
                .andExpect(jsonPath("$.forecast[0].date").value("2025-01-15"))
                .andExpect(jsonPath("$.forecast[0].tempMin").value(5.2))
                .andExpect(jsonPath("$.forecast[0].tempMax").value(14.8))
                .andExpect(jsonPath("$.forecast[0].condition").value("SUNNY"))
                .andExpect(jsonPath("$.forecast[0].precipitation").value(10))
                .andExpect(jsonPath("$.forecast[1].date").value("2025-01-16"))
                .andExpect(jsonPath("$.forecast[1].tempMin").value(3.1))
                .andExpect(jsonPath("$.forecast[1].tempMax").value(11.5))
                .andExpect(jsonPath("$.forecast[1].condition").value("RAINY"))
                .andExpect(jsonPath("$.forecast[1].precipitation").value(75));
    }

    private CurrentWeatherResponse buildCurrentWeatherResponse() {
        return CurrentWeatherResponse.builder()
                .city("Windholm")
                .timestamp("2025-01-14T15:32:00")
                .temperature(22.5)
                .humidity(65)
                .windSpeed(15.3)
                .condition(WeatherCondition.SUNNY)
                .build();
    }

    private ForecastResponse buildForecastResponse() {
        ForecastDay day1 = buildForecastDay("2025-01-15", 5.2, 14.8, WeatherCondition.SUNNY, 10);
        ForecastDay day2 = buildForecastDay("2025-01-16", 3.1, 11.5, WeatherCondition.RAINY, 75);

        return ForecastResponse.builder()
                .city("Windholm")
                .generatedAt("2025-01-14T15:32:00")
                .forecast(List.of(day1, day2))
                .build();
    }

    private ForecastDay buildForecastDay(String date, double tempMin, double tempMax,
                                         WeatherCondition condition, int precipitation) {
        return ForecastDay.builder()
                .date(date)
                .tempMin(tempMin)
                .tempMax(tempMax)
                .condition(condition)
                .precipitation(precipitation)
                .build();
    }

    @Test
    void shouldHandleIllegalArgumentException() throws Exception {
        String errorMessage = "Invalid weather data configuration";
        when(weatherService.getCurrentWeather()).thenThrow(new IllegalArgumentException(errorMessage));

        ResultActions result = mockMvc.perform(get("/api/weather/current"));

        result.andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Bad Request"))
                .andExpect(jsonPath("$.message").value(errorMessage))
                .andExpect(jsonPath("$.path").value("/api/weather/current"))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    void shouldHandleNullPointerException() throws Exception {
        when(weatherService.getForecast()).thenThrow(new NullPointerException("Null data encountered"));

        ResultActions result = mockMvc.perform(get("/api/weather/forecast"));

        result.andExpect(status().isInternalServerError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.error").value("Internal Server Error"))
                .andExpect(jsonPath("$.message").value("An internal error occurred due to missing data"))
                .andExpect(jsonPath("$.path").value("/api/weather/forecast"))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    void shouldHandleGenericException() throws Exception {
        when(weatherService.getCurrentWeather()).thenThrow(new RuntimeException("Unexpected system error"));

        ResultActions result = mockMvc.perform(get("/api/weather/current"));

        result.andExpect(status().isInternalServerError())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.error").value("Internal Server Error"))
                .andExpect(jsonPath("$.message").value("An unexpected error occurred while processing your request"))
                .andExpect(jsonPath("$.path").value("/api/weather/current"))
                .andExpect(jsonPath("$.timestamp").exists());
    }
}

