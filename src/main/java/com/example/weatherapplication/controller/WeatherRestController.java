package com.example.weatherapplication.controller;

import com.example.weatherapplication.BestWeather;
import com.example.weatherapplication.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherRestController {

    @Autowired
    WeatherService service;

    @GetMapping("/rs/getbestweather")
    public ResponseEntity<BestWeather> getbestweather() {
        return ResponseEntity.ok(service.returnBestWeather());
    }
}
