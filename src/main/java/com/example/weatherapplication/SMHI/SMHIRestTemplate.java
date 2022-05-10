package com.example.weatherapplication.SMHI;

import org.springframework.web.client.RestTemplate;

import java.util.List;

public class SMHIRestTemplate {

    private final String SMHI_URL = "https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/18.0300/lat/59.3110/data.json";
    private RestTemplate rt = new RestTemplate();
    private SMHI allSmhiData;
    private TimeSeries tomorrowsSmhiWeatherData;

    public SMHIRestTemplate(String tomorrowsDateAndTime) {
        allSmhiData = rt.getForObject(SMHI_URL, SMHI.class);
        tomorrowsSmhiWeatherData = getTomorrowsSmhiWeatherObject(tomorrowsDateAndTime);
    }

    public Double getSmhiTemperature() {
        Double temp = 0.0;
        for (Parameter p : tomorrowsSmhiWeatherData.getParameters()) {
            if (p.getName().equals("t")) {
                temp = p.getValues().get(0);
            }
        }
        return temp;
    }

    public Double getSmhiHumidity() {
        Double humidity = 0.0;
        for (Parameter p : tomorrowsSmhiWeatherData.getParameters()) {
            if (p.getName().equals("r")) {
                humidity = p.getValues().get(0);
            }
        }
        return humidity;
    }

    public Double getSmhiWindSpeed(){
        Double windSpeed = 0.0;
        for (Parameter p : tomorrowsSmhiWeatherData.getParameters()) {
            if (p.getName().equals("ws")) {
                windSpeed = p.getValues().get(0);
            }
        }
        return windSpeed;
    }

    public String getSmhiDateAndTime(){
        return tomorrowsSmhiWeatherData.getValidTime();
    }

    private TimeSeries getTomorrowsSmhiWeatherObject(String tomorrowsDateAndTime){
        List<TimeSeries> timeSeries = allSmhiData.getTimeSeries();

        TimeSeries t = new TimeSeries();
        for (TimeSeries time : timeSeries) {
            if (time.getValidTime().substring(0, 13).equals(tomorrowsDateAndTime)) {
                t = time;
            }
        }
        return t;
    }
}
