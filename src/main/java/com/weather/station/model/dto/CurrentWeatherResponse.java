package com.weather.station.model.dto;

import com.weather.station.model.enums.WeatherCondition;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrentWeatherResponse {
    private String city;
    private String timestamp;
    private Double temperature;
    private Integer humidity;
    private Double windSpeed;
    private WeatherCondition condition;
}
