package com.example.WeatherAnalyzer.mapper;

import com.example.weather_analyzer.WeatherAnalyzerApplication;
import com.example.weather_analyzer.dto.WeatherReadDto;
import com.example.weather_analyzer.mapper.WeatherReadMapper;
import com.example.weather_analyzer.model.entity.Weather;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = WeatherAnalyzerApplication.class)
public class WeatherReadMapperTest {
    @Autowired
    private WeatherReadMapper mapperUnderTest;

    @Test
    void shouldProperlyMapDtoToModel() {
        //given
        WeatherReadDto dto = WeatherReadDto.builder()
                .humidity(80)
                .location("Minsk")
                .weatherConditions("Cloudy")
                .build();

        //when
        Weather model = mapperUnderTest.toModel(dto);

        //then
        Assertions.assertNotNull(model);
        Assertions.assertEquals(dto.getLocation(), model.getLocation());
        Assertions.assertEquals(dto.getHumidity(), model.getHumidity());
        Assertions.assertEquals(dto.getPressure(), model.getPressure());
    }

    @Test
    void shouldProperlyMapModelToDto() {
        //given
        Weather model = Weather.builder()
                .humidity(80)
                .location("Minsk")
                .weatherConditions("Cloudy")
                .build();

        //when
        WeatherReadDto dto  = mapperUnderTest.toDto(model);

        //then
        Assertions.assertNotNull(model);
        Assertions.assertEquals(dto.getLocation(), model.getLocation());
        Assertions.assertEquals(dto.getHumidity(), model.getHumidity());
        Assertions.assertEquals(dto.getPressure(), model.getPressure());
    }
}
