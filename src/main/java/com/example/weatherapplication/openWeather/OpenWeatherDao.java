package com.example.weatherapplication.openWeather;

import com.example.weatherapplication.OurWeather;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.WebServiceClient;

@Component
public class OpenWeatherDao {

    private final String URL = "https://api.openweathermap.org/data/2.5/onecall?lat=59.3110&lon=18.0300&exclude=current,minutely&appid=8a267d2b812175cee9df68624d6f13af&units=metric";
    private RestTemplate rt = new RestTemplate();

    public OurWeather getTomorrowsWeatherObject() {
        OpenWeather allOpenWeatherData = rt.getForObject(URL, OpenWeather.class);
        Hourly hourly = allOpenWeatherData.getHourly().get(24);
        String time = new java.util.Date((long) hourly.getDt() * 1000).toString();

        return new OurWeather("Open Weather",
                time,
                hourly.getTemp(),
                hourly.getWindSpeed(),
                Double.valueOf(hourly.getHumidity()));
    }
}
