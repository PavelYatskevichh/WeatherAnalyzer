package com.example.weather_analyzer.service.impl;

import com.example.weather_analyzer.dto.WeatherCreateDto;
import com.example.weather_analyzer.mapper.WeatherCreateMapper;
import com.example.weather_analyzer.model.entity.Weather;
import com.example.weather_analyzer.model.repository.WeatherRepository;
import com.example.weather_analyzer.service.WeatherApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:weather-api.properties")
public class WeatherApiServiceImpl implements WeatherApiService {
    private final WeatherCreateMapper weatherCreateMapper;
    private final WeatherRepository weatherRepository;
    private final RestTemplate restTemplate;
    @Value("${weatherApi.url}")
    private String apiUrl;

    @Override
    @Scheduled(fixedRate = 900000)
    public void fetchAndSaveWeather() {
        WeatherCreateDto weatherCreateDto = restTemplate.getForObject(apiUrl, WeatherCreateDto.class);
        Weather currentWeather = weatherCreateMapper.toModel(weatherCreateDto);
        weatherRepository.save(currentWeather);
    }
}
