package com.example.WeatherAnalyzer.service.impl;

import com.example.weather_analyzer.WeatherAnalyzerApplication;
import com.example.weather_analyzer.dto.AverageTempReadDto;
import com.example.weather_analyzer.dto.WeatherReadDto;
import com.example.weather_analyzer.service.WeatherService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = WeatherAnalyzerApplication.class)
public class WeatherServiceImplTest {
    @Autowired
    private WeatherService weatherService;

    @Test
    void getCurrentWeatherTest() {
        //given
        String location = "Minsk";

        //when
        WeatherReadDto weatherReadDto = weatherService.getCurrentWeather(location);

        //then
        Assertions.assertNotNull(weatherReadDto);
    }

    @Test
    void getAverageTemperatureForPeriodTest() {
        //given
        String location = "Minsk";
        String from = "19-12-2023";
        String to = "19-12-2023";

        //when
        AverageTempReadDto averageTempReadDto = weatherService.getAverageTemperatureForPeriod(location, from, to);

        //then
        Assertions.assertNotNull(averageTempReadDto);
    }
}
