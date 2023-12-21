package com.example.weather_analyzer.model.repository;

import com.example.weather_analyzer.model.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather, Integer> {
    @Query(value = "SELECT AVG(temperature) FROM Weather " +
            "WHERE date BETWEEN :from AND :to " +
            "AND location = :location")
    Optional<Double> calculateAverageTempForDate(@Param("location") String location,
                                                 @Param("from") LocalDate from,
                                                 @Param("to") LocalDate to);
    Optional<Weather> findTopByLocationOrderByIdDesc(@Param("location") String location);
}