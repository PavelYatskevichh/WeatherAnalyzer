package com.example.WeatherAnalyzer.model.repository;

import com.example.weather_analyzer.WeatherAnalyzerApplication;
import com.example.weather_analyzer.model.entity.Weather;
import com.example.weather_analyzer.model.repository.WeatherRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WeatherAnalyzerApplication.class)
public class WeatherRepositoryTest {

    @Autowired
    private WeatherRepository weatherRepository;

    @Test
    public void testCalculateAverageTempForDate() {
        LocalDate from = LocalDate.of(2023, 1, 1);
        LocalDate to = LocalDate.of(2023, 12, 31);
        String location = "Minsk";

        Optional<Double> averageTemp = weatherRepository.calculateAverageTempForDate(location, from, to);

        Assertions.assertNotNull(averageTemp);
    }

    @Test
    public void testFindTopByLocationOrderByIdDesc() {
        String location = "Minsk";

        Optional<Weather> weather = weatherRepository.findTopByLocationOrderByIdDesc(location);

        Assertions.assertNotNull(weather);
    }
}