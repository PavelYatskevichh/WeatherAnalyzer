package com.example.weather_analyzer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherCreateDto {
    @JsonProperty("location")
    private LocationDto location;
    @JsonProperty("current")
    private CurrentDto current;
}
