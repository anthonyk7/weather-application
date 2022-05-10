package com.example.weatherapplication.controller;

import com.example.weatherapplication.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherController {

    @Autowired
    WeatherService service;

    @GetMapping
    public String getBestWeather(Model model){
        model.addAttribute("bestWeather", service.returnBestWeather());
        return "index";
    }
}
