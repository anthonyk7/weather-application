package com.example.weatherapplication.service;

import com.example.weatherapplication.OurWeather;
import com.example.weatherapplication.MET.MetDao;
import com.example.weatherapplication.SMHI.SmhiDao;
import com.example.weatherapplication.openWeather.OpenWeatherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    @Autowired
    private SmhiDao smhiDao;

    @Autowired
    private MetDao metDao;

    @Autowired
    private OpenWeatherDao openWeatherDao;

    private int smhiCounter;
    private int metCounter;
    private int openWeatherCounter;

//jämnför alla 3s score för att ta fram bästa vädret
    public OurWeather returnBestWeather() {
        compareWeatherData();
        OurWeather bestWeather = null;

        if (smhiCounter > metCounter && smhiCounter > openWeatherCounter) {
            bestWeather = smhiDao.getTomorrowsWeatherObject();
        } else if (metCounter > smhiCounter && metCounter > openWeatherCounter) {
            bestWeather = metDao.getTomorrowsWeatherObject();
        } else if (openWeatherCounter > smhiCounter && openWeatherCounter > metCounter) {
            bestWeather = openWeatherDao.getTomorrowsWeatherObject();
        }

        return bestWeather;
    }

//jämnför smhi mot met
    public void compareWeatherData() {
        if (smhiDao.getTomorrowsWeatherObject().getTemperature() > metDao.getTomorrowsWeatherObject().getTemperature())
            smhiCounter += 4;
        else if (metDao.getTomorrowsWeatherObject().getTemperature() > smhiDao.getTomorrowsWeatherObject().getTemperature())
            metCounter += 4;

        if (smhiDao.getTomorrowsWeatherObject().getRelativeHumidity() < metDao.getTomorrowsWeatherObject().getRelativeHumidity())
            smhiCounter += 3;
        else if (metDao.getTomorrowsWeatherObject().getRelativeHumidity() < smhiDao.getTomorrowsWeatherObject().getRelativeHumidity())
            metCounter += 3;

        if (smhiDao.getTomorrowsWeatherObject().getWindSpeed() < metDao.getTomorrowsWeatherObject().getWindSpeed())
            smhiCounter += 2;
        else if (metDao.getTomorrowsWeatherObject().getWindSpeed() < smhiDao.getTomorrowsWeatherObject().getWindSpeed())
            metCounter += 2;

        //vinnare av smhi mot met möter openWeather
        if (smhiCounter > metCounter) {
            metCounter = 0;
            smhiCounter = 0;
            if (smhiDao.getTomorrowsWeatherObject().getTemperature() > openWeatherDao.getTomorrowsWeatherObject().getTemperature())
                smhiCounter += 4;
            else if (openWeatherDao.getTomorrowsWeatherObject().getTemperature() > smhiDao.getTomorrowsWeatherObject().getTemperature())
                openWeatherCounter += 4;

            if (smhiDao.getTomorrowsWeatherObject().getRelativeHumidity() < openWeatherDao.getTomorrowsWeatherObject().getRelativeHumidity())
                smhiCounter += 3;
            else if (openWeatherDao.getTomorrowsWeatherObject().getRelativeHumidity() < smhiDao.getTomorrowsWeatherObject().getRelativeHumidity())
                openWeatherCounter += 3;

            if (smhiDao.getTomorrowsWeatherObject().getWindSpeed() < openWeatherDao.getTomorrowsWeatherObject().getWindSpeed())
                smhiCounter += 2;
            else if (openWeatherDao.getTomorrowsWeatherObject().getWindSpeed() < smhiDao.getTomorrowsWeatherObject().getWindSpeed())
                openWeatherCounter += 2;
        } else if (metCounter > smhiCounter) {
            metCounter = 0;
            smhiCounter = 0;
            if (openWeatherDao.getTomorrowsWeatherObject().getTemperature() > metDao.getTomorrowsWeatherObject().getTemperature())
                openWeatherCounter += 4;
            else if (metDao.getTomorrowsWeatherObject().getTemperature() > openWeatherDao.getTomorrowsWeatherObject().getTemperature())
                metCounter += 4;

            if (openWeatherDao.getTomorrowsWeatherObject().getRelativeHumidity() < metDao.getTomorrowsWeatherObject().getRelativeHumidity())
                openWeatherCounter += 3;
            else if (metDao.getTomorrowsWeatherObject().getRelativeHumidity() < openWeatherDao.getTomorrowsWeatherObject().getRelativeHumidity())
                metCounter += 3;

            if (openWeatherDao.getTomorrowsWeatherObject().getWindSpeed() < metDao.getTomorrowsWeatherObject().getWindSpeed())
                openWeatherCounter += 2;
            else if (metDao.getTomorrowsWeatherObject().getWindSpeed() < openWeatherDao.getTomorrowsWeatherObject().getWindSpeed())
                metCounter += 2;
        }
    }
}
