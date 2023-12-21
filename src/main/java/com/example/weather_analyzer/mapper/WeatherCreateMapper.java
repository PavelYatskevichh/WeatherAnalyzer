package com.example.weather_analyzer.mapper;

import com.example.weather_analyzer.dto.WeatherCreateDto;
import com.example.weather_analyzer.dto.WeatherReadDto;
import com.example.weather_analyzer.model.entity.Weather;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface WeatherCreateMapper {
    @Mapping(target = "location", source = "weatherCreateDto.location.name")
    @Mapping(target = "date", expression = "java(parseDate(weatherCreateDto))")
    @Mapping(target = "time", expression = "java(parseTime(weatherCreateDto))")
    @Mapping(target = "temperature", source = "weatherCreateDto.current.tempC")
    @Mapping(target = "windSpeed", source = "weatherCreateDto.current.windKph")
    @Mapping(target = "pressure", source = "weatherCreateDto.current.pressureMb")
    @Mapping(target = "humidity", source = "weatherCreateDto.current.humidity")
    @Mapping(target = "weatherConditions", source = "weatherCreateDto.current.condition.text")
    Weather toModel(WeatherCreateDto weatherCreateDto);

    default LocalDate parseDate(WeatherCreateDto weatherCreateDto) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");
        return LocalDate.parse(weatherCreateDto.getCurrent().getLastUpdated(), dateFormatter);
    }

    default LocalTime parseTime(WeatherCreateDto weatherCreateDto) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");
        return LocalTime.parse(weatherCreateDto.getCurrent().getLastUpdated(), dateFormatter);
    }
}
