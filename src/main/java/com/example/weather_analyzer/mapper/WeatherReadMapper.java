package com.example.weather_analyzer.mapper;

import com.example.weather_analyzer.dto.WeatherCreateDto;
import com.example.weather_analyzer.dto.WeatherReadDto;
import com.example.weather_analyzer.model.entity.Weather;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeatherReadMapper {
    WeatherReadDto toDto(Weather weather);
    Weather toModel(WeatherReadDto weatherReadDto);
}
