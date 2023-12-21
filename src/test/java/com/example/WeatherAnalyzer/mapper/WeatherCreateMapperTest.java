package com.example.WeatherAnalyzer.mapper;

import com.example.weather_analyzer.WeatherAnalyzerApplication;
import com.example.weather_analyzer.dto.CurrentDto;
import com.example.weather_analyzer.dto.LocationDto;
import com.example.weather_analyzer.dto.WeatherCreateDto;
import com.example.weather_analyzer.mapper.WeatherCreateMapper;
import com.example.weather_analyzer.model.entity.Weather;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = WeatherAnalyzerApplication.class)
public class WeatherCreateMapperTest {

    @Autowired
    private WeatherCreateMapper mapperUnderTest;

    @Test
    void shouldProperlyMapDtoToModel() {
        //given
        WeatherCreateDto dto = WeatherCreateDto.builder()
                .location(LocationDto.builder()
                        .name("Minsk")
                        .build())
                .current(CurrentDto.builder()
                        .humidity(80)
                        .build())
                .build();

        //when
        Weather model = mapperUnderTest.toModel(dto);

        //then
        Assertions.assertNotNull(model);
        Assertions.assertEquals(dto.getLocation().getName(), model.getLocation());
        Assertions.assertEquals(dto.getCurrent().getHumidity(), model.getHumidity());
    }
}
