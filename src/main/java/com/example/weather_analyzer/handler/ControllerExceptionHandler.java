package com.example.weather_analyzer.handler;

import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages = "com.example.weather_analyzer.controller")
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

}
