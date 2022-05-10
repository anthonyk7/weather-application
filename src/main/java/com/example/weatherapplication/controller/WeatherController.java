package com.example.weatherapplication.controller;

import com.example.weatherapplication.service.WeatherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WeatherController {


    @GetMapping
    public String getBestWeather(Model model){
        WeatherService service = new WeatherService();
        model.addAttribute("bestWeather", service.returnBestWeather());
        return "index";
    }
}
