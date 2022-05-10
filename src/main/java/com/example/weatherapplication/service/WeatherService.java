package com.example.weatherapplication.service;

import com.example.weatherapplication.BestWeather;
import com.example.weatherapplication.MET.METRestTemplate;
import com.example.weatherapplication.SMHI.SMHIRestTemplate;
import com.example.weatherapplication.openWeather.OpenWeatherRestTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class WeatherService {

    private SMHIRestTemplate smhiRestTemplate;
    private METRestTemplate metRestTemplate;
    private OpenWeatherRestTemplate openWeatherRestTemplate;

    private int smhiCounter;
    private int metCounter;
    private int openWeatherCounter;

    public WeatherService() {
        String tomorrowsDateAndTime = LocalDateTime.now()
                .plusDays(1L)
                .format(DateTimeFormatter.ISO_DATE_TIME)
                .substring(0, 13);

        smhiRestTemplate = new SMHIRestTemplate(tomorrowsDateAndTime);
        metRestTemplate = new METRestTemplate(tomorrowsDateAndTime);
        openWeatherRestTemplate = new OpenWeatherRestTemplate();
    }

    public BestWeather returnBestWeather() {
        compareWeatherData();
        BestWeather bestWeather = null;

        if (smhiCounter > metCounter && smhiCounter > openWeatherCounter) {
            bestWeather = new BestWeather("SMHI",
                    smhiRestTemplate.getSmhiDateAndTime(),
                    smhiRestTemplate.getSmhiTemperature(),
                    smhiRestTemplate.getSmhiWindSpeed(),
                    smhiRestTemplate.getSmhiHumidity());
        } else if (metCounter > smhiCounter && metCounter > openWeatherCounter) {
            bestWeather = new BestWeather("MET",
                    metRestTemplate.getMetDateAndTime(),
                    metRestTemplate.getMetTemperature(),
                    metRestTemplate.getMetWindSpeed(),
                    metRestTemplate.getMetHumidity());
        } else if (openWeatherCounter > smhiCounter && openWeatherCounter > metCounter) {
            bestWeather = new BestWeather("Open Weather",
                    openWeatherRestTemplate.getOpenWeatherDateAndTime(),
                    openWeatherRestTemplate.getOpenWeatherTemperature(),
                    openWeatherRestTemplate.getOpenWeatherWindSpeed(),
                    openWeatherRestTemplate.getOpenWeatherHumidity());
        }

        return bestWeather;
    }

    public void compareWeatherData() {
        if (smhiRestTemplate.getSmhiTemperature() > metRestTemplate.getMetTemperature())
            smhiCounter += 4;
        else if (metRestTemplate.getMetTemperature() > smhiRestTemplate.getSmhiTemperature())
            metCounter += 4;

        if (smhiRestTemplate.getSmhiHumidity() < metRestTemplate.getMetHumidity())
            smhiCounter += 3;
        else if (metRestTemplate.getMetHumidity() < smhiRestTemplate.getSmhiHumidity())
            metCounter += 3;

        if (smhiRestTemplate.getSmhiWindSpeed() < metRestTemplate.getMetWindSpeed())
            smhiCounter += 2;
        else if (metRestTemplate.getMetWindSpeed() < smhiRestTemplate.getSmhiWindSpeed())
            metCounter += 2;


        if (smhiCounter > metCounter) {
            metCounter = 0;
            smhiCounter = 0;
            if (smhiRestTemplate.getSmhiTemperature() > openWeatherRestTemplate.getOpenWeatherTemperature())
                smhiCounter += 4;
            else if (openWeatherRestTemplate.getOpenWeatherTemperature() > smhiRestTemplate.getSmhiTemperature())
                openWeatherCounter += 4;

            if (smhiRestTemplate.getSmhiHumidity() < openWeatherRestTemplate.getOpenWeatherHumidity())
                smhiCounter += 3;
            else if (openWeatherRestTemplate.getOpenWeatherHumidity() < smhiRestTemplate.getSmhiHumidity())
                openWeatherCounter += 3;

            if (smhiRestTemplate.getSmhiWindSpeed() < openWeatherRestTemplate.getOpenWeatherWindSpeed())
                smhiCounter += 2;
            else if (openWeatherRestTemplate.getOpenWeatherWindSpeed() < smhiRestTemplate.getSmhiWindSpeed())
                openWeatherCounter += 2;
        } else if (metCounter > smhiCounter) {
            metCounter = 0;
            smhiCounter = 0;
            if (openWeatherRestTemplate.getOpenWeatherTemperature() > metRestTemplate.getMetTemperature())
                openWeatherCounter += 4;
            else if (metRestTemplate.getMetTemperature() > openWeatherRestTemplate.getOpenWeatherTemperature())
                metCounter += 4;

            if (openWeatherRestTemplate.getOpenWeatherHumidity() < metRestTemplate.getMetHumidity())
                openWeatherCounter += 3;
            else if (metRestTemplate.getMetHumidity() < openWeatherRestTemplate.getOpenWeatherHumidity())
                metCounter += 3;

            if (openWeatherRestTemplate.getOpenWeatherWindSpeed() < metRestTemplate.getMetWindSpeed())
                openWeatherCounter += 2;
            else if (metRestTemplate.getMetWindSpeed() < openWeatherRestTemplate.getOpenWeatherWindSpeed())
                metCounter += 2;
        }
    }
}
