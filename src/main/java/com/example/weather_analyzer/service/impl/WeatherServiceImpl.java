package com.example.weather_analyzer.service.impl;

import com.example.weather_analyzer.dto.AverageTempReadDto;
import com.example.weather_analyzer.dto.WeatherCreateDto;
import com.example.weather_analyzer.dto.WeatherReadDto;
import com.example.weather_analyzer.mapper.WeatherCreateMapper;
import com.example.weather_analyzer.mapper.WeatherReadMapper;
import com.example.weather_analyzer.model.entity.Weather;
import com.example.weather_analyzer.model.repository.WeatherRepository;
import com.example.weather_analyzer.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    private final WeatherReadMapper weatherReadMapper;
    private final WeatherCreateMapper weatherCreateMapper;
    private final WeatherRepository weatherRepository;
    private final RestTemplate restTemplate;

    @Override
    public WeatherReadDto getCurrentWeather(String location) {
        return weatherReadMapper.toDto(weatherRepository.findTopByLocationOrderByIdDesc(location));
    }

    @Override
    public AverageTempReadDto getAverageTemperatureForPeriod(String location, String from, String to) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateFrom = LocalDate.parse(from, dateFormatter);
        LocalDate dateTo = LocalDate.parse(to, dateFormatter);

        Double result = weatherRepository.calculateAverageTempForDate(location, dateFrom, dateTo);
        result = new BigDecimal(result).setScale(1, RoundingMode.HALF_UP).doubleValue();
        return new AverageTempReadDto(result);
    }

    @Scheduled(fixedRate = 900000)
    public void fetchAndSaveWeather() {
        String apiUrl = "http://api.weatherapi.com/v1/current.json?key=4f2f695d74d545208db215926231812&q=minsk";
        WeatherCreateDto weatherCreateDto = restTemplate.getForObject(apiUrl, WeatherCreateDto.class);
        Weather currentWeather = weatherCreateMapper.toModel(weatherCreateDto);
        weatherRepository.save(currentWeather);
    }
}
