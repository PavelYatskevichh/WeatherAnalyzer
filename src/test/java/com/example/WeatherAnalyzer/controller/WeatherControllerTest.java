package com.example.WeatherAnalyzer.controller;

import com.example.weather_analyzer.WeatherAnalyzerApplication;
import com.example.weather_analyzer.controller.WeatherController;
import com.example.weather_analyzer.dto.AverageTempReadDto;
import com.example.weather_analyzer.dto.WeatherReadDto;
import com.example.weather_analyzer.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest(classes = WeatherAnalyzerApplication.class)
@AutoConfigureMockMvc
public class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherController weatherController;

    @Test
    public void testGetCurrentWeather() throws Exception {
        String location = "Minsk";
        WeatherReadDto weatherReadDto = WeatherReadDto.builder()
                .location("Minsk")
                .temperature(2.0)
                .windSpeed(5.0)
                .pressure(966.0)
                .humidity(80)
                .weatherConditions("Sunny")
                .build();

        BDDMockito.given(weatherService.getCurrentWeather(location)).willReturn(weatherReadDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/weather/current")
                        .param("location", location)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.location").value("Minsk"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperature").value(2.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.wind_speed").value(5.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.pressure").value(966.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.humidity").value(80))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weather_conditions").value("Sunny"));
    }

    @Test
    public void testGetAverageTemperature() throws Exception {
        String location = "Minsk";
        String from = "15-12-2023";
        String to = "17-12-2023";
        AverageTempReadDto averageTempReadDto = new AverageTempReadDto(20.0);

        BDDMockito.given(weatherService.getAverageTemperatureForPeriod(location, from, to)).willReturn(averageTempReadDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/weather/average-temp")
                        .param("location", location)
                        .param("from", from)
                        .param("to", to)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.average_temperature").value(20.0));
    }
}
