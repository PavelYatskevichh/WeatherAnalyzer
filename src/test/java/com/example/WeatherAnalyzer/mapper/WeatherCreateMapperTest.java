package com.example.WeatherAnalyzer.mapper;

import com.example.weather_analyzer.WeatherAnalyzerApplication;
import com.example.weather_analyzer.dto.ConditionDto;
import com.example.weather_analyzer.dto.CurrentDto;
import com.example.weather_analyzer.dto.LocationDto;
import com.example.weather_analyzer.dto.WeatherCreateDto;
import com.example.weather_analyzer.mapper.WeatherCreateMapper;
import com.example.weather_analyzer.model.entity.Weather;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = WeatherAnalyzerApplication.class)
public class WeatherCreateMapperTest {

    @Autowired
    private WeatherCreateMapper mapper;

    @Test
    public void testMapping() {
        WeatherCreateDto weatherCreateDto = WeatherCreateDto.builder()
                .location(LocationDto.builder()
                        .name("Minsk")
                        .build())
                .current(CurrentDto.builder()
                        .lastUpdated("2023-12-15 2:15")
                        .tempC(5.0)
                        .windKph(35.0)
                        .precipMm(966.0)
                        .condition(ConditionDto.builder()
                                .text("Cloudy")
                                .build())
                        .humidity(80)
                        .build())
                .build();

        Weather weather = mapper.toModel(weatherCreateDto);

        assertEquals(weather.getLocation(), weatherCreateDto.getLocation().getName());
        assertEquals(weather.getDate(), parseDate(weatherCreateDto));
        assertEquals(weather.getTime(), parseTime(weatherCreateDto));
        assertEquals(weather.getTemperature(), weatherCreateDto.getCurrent().getTempC());
        assertEquals(weather.getWindSpeed(), weatherCreateDto.getCurrent().getWindKph());
        assertEquals(weather.getPressure(), weatherCreateDto.getCurrent().getPressureMb());
        assertEquals(weather.getHumidity(), weatherCreateDto.getCurrent().getHumidity());
        assertEquals(weather.getWeatherConditions(), weatherCreateDto.getCurrent().getCondition().getText());
    }

    private LocalDate parseDate(WeatherCreateDto weatherCreateDto) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");
        return LocalDate.parse(weatherCreateDto.getCurrent().getLastUpdated(), dateFormatter);
    }

    private LocalTime parseTime(WeatherCreateDto weatherCreateDto) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H:mm");
        return LocalTime.parse(weatherCreateDto.getCurrent().getLastUpdated(), dateFormatter);
    }
}
