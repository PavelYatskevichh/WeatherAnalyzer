package com.example.weather_analyzer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherReadDto {
    @JsonProperty("location")
    private String location;
    @JsonProperty("temperature")
    private Double temperature;
    @JsonProperty("wind_speed")
    private Double windSpeed;
    @JsonProperty("pressure")
    private Double pressure;
    @JsonProperty("humidity")
    private Integer humidity;
    @JsonProperty("weather_conditions")
    private String weatherConditions;
}
