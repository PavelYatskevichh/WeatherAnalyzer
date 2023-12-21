package com.example.weather_analyzer.controller;

import com.example.weather_analyzer.dto.AverageTempReadDto;
import com.example.weather_analyzer.dto.WeatherReadDto;
import com.example.weather_analyzer.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/weather")
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping(value = "/current")
    public WeatherReadDto getCurrentWeather (@RequestParam String location) {
        return weatherService.getCurrentWeather(location);
    }

    @GetMapping(value = "/average-temp")
    public AverageTempReadDto getAverageTemperature (@RequestParam String location,
                                                     @RequestParam String from,
                                                     @RequestParam String to) {
        return weatherService.getAverageTemperatureForPeriod(location, from, to);
    }
}
