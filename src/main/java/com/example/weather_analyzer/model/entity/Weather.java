package com.example.weather_analyzer.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "weather")
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "location")
    private String location;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "time")
    private LocalTime time;
    @Column(name = "temperature")
    private Double temperature;
    @Column(name = "wind_speed")
    private Double windSpeed;
    @Column(name = "pressure")
    private Double pressure;
    @Column(name = "humidity")
    private Integer humidity;
    @Column(name = "weather_conditions")
    private String weatherConditions;
}
