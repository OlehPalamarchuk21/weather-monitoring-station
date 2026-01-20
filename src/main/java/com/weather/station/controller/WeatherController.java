package com.weather.station.controller;

import com.weather.station.model.dto.CurrentWeatherResponse;
import com.weather.station.model.dto.ForecastResponse;
import com.weather.station.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/current")
    public CurrentWeatherResponse getCurrentWeather() {
        return weatherService.getCurrentWeather();
    }

    @GetMapping("/forecast")
    public ForecastResponse getForecast() {
        return weatherService.getForecast();
    }
}
