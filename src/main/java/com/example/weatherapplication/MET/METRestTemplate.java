package com.example.weatherapplication.MET;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import java.util.List;

public class METRestTemplate {

    private final String MET_URL = "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=59.3110&lon=18.0300";
    private RestTemplate rt = new RestTemplateBuilder()
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader(HttpHeaders.USER_AGENT, "notJava!")
            .build();
    private MET allMetData;
    private Timeseries tomorrowsMetWeatherData;

    public METRestTemplate(String tomorrowsDateAndTime) {
        allMetData = rt.getForObject(MET_URL, MET.class);
        tomorrowsMetWeatherData = getTomorrowsMetWeatherObject(tomorrowsDateAndTime);
    }

    public Double getMetTemperature() {
        return tomorrowsMetWeatherData.getData().getInstant().getDetails().getAirTemperature();
    }

    public Double getMetHumidity() {
        return tomorrowsMetWeatherData.getData().getInstant().getDetails().getRelativeHumidity();
    }

    public Double getMetWindSpeed (){
        return tomorrowsMetWeatherData.getData().getInstant().getDetails().getWindSpeed();
    }

    public String getMetDateAndTime(){
        return tomorrowsMetWeatherData.getTime();
    }

    private Timeseries getTomorrowsMetWeatherObject(String tomorrowsDateAndTime){
        List<Timeseries> timeseries = allMetData.getProperties().getTimeseries();
        Timeseries t = new Timeseries();

        for (Timeseries time : timeseries) {
            if (time.getTime().substring(0, 13).equals(tomorrowsDateAndTime)) {
                t = time;
            }
        }
        return t;
    }
}
