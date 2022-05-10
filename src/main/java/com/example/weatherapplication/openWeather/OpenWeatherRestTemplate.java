package com.example.weatherapplication.openWeather;

import org.springframework.web.client.RestTemplate;

import java.util.List;

public class OpenWeatherRestTemplate {

    private final String OPENWEATHER_URL = "https://api.openweathermap.org/data/2.5/onecall?lat=59.3110&lon=18.0300&exclude=current,minutely&appid=8a267d2b812175cee9df68624d6f13af&units=metric";
    private RestTemplate rt = new RestTemplate();
    private OpenWeather allOpenWeatherData;
    private Hourly tomorrowsOpenWeatherData;

    public OpenWeatherRestTemplate() {
        allOpenWeatherData = rt.getForObject(OPENWEATHER_URL, OpenWeather.class);
        tomorrowsOpenWeatherData = getTomorrowsOpenWeatherObject();
    }

    public Double getOpenWeatherTemperature() {
        return tomorrowsOpenWeatherData.getTemp();
    }

    public Double getOpenWeatherHumidity() {
        Double doubleTemp = Double.valueOf(tomorrowsOpenWeatherData.getHumidity());
        return doubleTemp;
    }

    public Double getOpenWeatherWindSpeed() {
        return tomorrowsOpenWeatherData.getWindSpeed();
    }

    public String getOpenWeatherDateAndTime() {
        Integer timeStamp = tomorrowsOpenWeatherData.getDt();
        java.util.Date time = new java.util.Date((long) timeStamp * 1000);
        return time.toString();
    }

    private Hourly getTomorrowsOpenWeatherObject() {
        List<Hourly> hourly = allOpenWeatherData.getHourly();
        return hourly.get(24);
    }
}
