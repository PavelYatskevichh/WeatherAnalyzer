package com.example.weather_analyzer.service.impl;

import com.example.weather_analyzer.dto.AverageTempReadDto;
import com.example.weather_analyzer.dto.WeatherReadDto;
import com.example.weather_analyzer.mapper.WeatherReadMapper;
import com.example.weather_analyzer.model.repository.WeatherRepository;
import com.example.weather_analyzer.service.WeatherService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {
    private final WeatherReadMapper weatherReadMapper;
    private final WeatherRepository weatherRepository;

    @Override
    public WeatherReadDto getCurrentWeather(String location) {
        return weatherReadMapper.toDto(weatherRepository.findTopByLocationOrderByIdDesc(location)
                .orElseThrow(() -> new EntityNotFoundException("Current weather not found.")));
    }

    @Override
    public AverageTempReadDto getAverageTemperatureForPeriod(String location, String from, String to) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateFrom = LocalDate.parse(from, dateFormatter);
        LocalDate dateTo = LocalDate.parse(to, dateFormatter);

        Double result = weatherRepository.calculateAverageTempForDate(location, dateFrom, dateTo)
                .orElseThrow(() -> new EntityNotFoundException("Weather for period from " + from + " to " + to + " not found."));
        result = new BigDecimal(result).setScale(1, RoundingMode.HALF_UP).doubleValue();
        return new AverageTempReadDto(result);
    }
}
