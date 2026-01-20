package com.weather.station.service;

import com.weather.station.config.WeatherConfig;
import com.weather.station.model.dto.CurrentWeatherResponse;
import com.weather.station.model.dto.ForecastDay;
import com.weather.station.model.dto.ForecastResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for orchestrating weather data operations.
 * <p>
 * This service coordinates the generation of current weather and forecast data
 * using the {@link WeatherDataGenerator} and applies configuration from {@link WeatherConfig}.
 * </p>
 *
 * @since 1.0.0
 */
@Service
public class WeatherService {

    private final WeatherDataGenerator dataGenerator;
    private final WeatherConfig weatherConfig;

    public WeatherService(WeatherDataGenerator dataGenerator, WeatherConfig weatherConfig) {
        this.dataGenerator = dataGenerator;
        this.weatherConfig = weatherConfig;
    }

    /**
     * Retrieves current weather conditions with randomized values.
     *
     * @return current weather response with city name from configuration
     */
    public CurrentWeatherResponse getCurrentWeather() {
        return CurrentWeatherResponse.builder()
                .city(weatherConfig.getCity().getName())
                .timestamp(dataGenerator.generateCurrentTimestamp())
                .temperature(dataGenerator.generateTemperature())
                .humidity(dataGenerator.generateHumidity())
                .windSpeed(dataGenerator.generateWindSpeed())
                .condition(dataGenerator.generateCondition())
                .build();
    }

    /**
     * Generates a 7-day weather forecast with randomized predictions.
     * <p>
     * Ensures that maximum temperature is always greater than or equal to minimum temperature.
     * </p>
     *
     * @return forecast response containing 7 days of weather predictions
     */
    public ForecastResponse getForecast() {
        List<String> forecastDates = dataGenerator.generateForecastDates();
        List<ForecastDay> forecast = new ArrayList<>();

        for (String date : forecastDates) {
            double tempMin = dataGenerator.generateTempMin();
            double tempMax = dataGenerator.generateTempMax(tempMin);

            ForecastDay forecastDay = ForecastDay.builder()
                    .date(date)
                    .tempMin(tempMin)
                    .tempMax(tempMax)
                    .condition(dataGenerator.generateCondition())
                    .precipitation(dataGenerator.generatePrecipitation())
                    .build();

            forecast.add(forecastDay);
        }

        return ForecastResponse.builder()
                .city(weatherConfig.getCity().getName())
                .generatedAt(dataGenerator.generateCurrentTimestamp())
                .forecast(forecast)
                .build();
    }
}
