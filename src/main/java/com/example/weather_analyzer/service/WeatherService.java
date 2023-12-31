package com.example.weather_analyzer.service;

import com.example.weather_analyzer.dto.AverageTempReadDto;
import com.example.weather_analyzer.dto.WeatherReadDto;

public interface WeatherService {
    WeatherReadDto getCurrentWeather(String city);
    AverageTempReadDto getAverageTemperatureForPeriod(String city, String from, String to);
}
