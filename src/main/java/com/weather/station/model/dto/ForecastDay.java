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
public class ForecastDay {
    private String date;
    private Double tempMin;
    private Double tempMax;
    private WeatherCondition condition;
    private Integer precipitation;
}
