package com.example.weather_analyzer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherReadDto {
    @JsonProperty("location")
    private String location;
    @JsonProperty("temperature")
    private Integer temperature;
    @JsonProperty("wind_speed")
    private Integer windSpeed;
    @JsonProperty("pressure")
    private Integer pressure;
    @JsonProperty("humidity")
    private Integer humidity;
    @JsonProperty("weather_conditions")
    private String weatherConditions;
}
