package com.example.weatherapplication.SMHI;

import com.example.weatherapplication.OurWeather;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class SmhiDao {

    private final String URL = "https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/18.0300/lat/59.3110/data.json";
    private RestTemplate rt = new RestTemplate();

    public OurWeather getTomorrowsWeatherObject() {
        String tomorrowsDateAndTime = LocalDateTime.now()
                .plusDays(1L)
                .format(DateTimeFormatter.ISO_DATE_TIME)
                .substring(0, 13);

        SMHI allSmhiData = rt.getForObject(URL, SMHI.class);
        List<TimeSeries> timeSeries = allSmhiData.getTimeSeries();

        TimeSeries t = new TimeSeries();
        for (TimeSeries time : timeSeries) {
            if (time.getValidTime().substring(0, 13).equals(tomorrowsDateAndTime)) {
                t = time;
            }
        }
        return new OurWeather("SMHI",
                t.getValidTime(),
                getSmhiTemperature(t),
                getSmhiHumidity(t),
                getSmhiWindSpeed(t));
    }

    private Double getSmhiTemperature(TimeSeries t) {
        Double temp = 0.0;
        for (Parameter p : t.getParameters()) {
            if (p.getName().equals("t")) {
                temp = p.getValues().get(0);
            }
        }
        return temp;
    }

    private Double getSmhiHumidity(TimeSeries t) {
        Double humidity = 0.0;
        for (Parameter p : t.getParameters()) {
            if (p.getName().equals("r")) {
                humidity = p.getValues().get(0);
            }
        }
        return humidity;
    }

    private Double getSmhiWindSpeed(TimeSeries t) {
        Double windSpeed = 0.0;
        for (Parameter p : t.getParameters()) {
            if (p.getName().equals("ws")) {
                windSpeed = p.getValues().get(0);
            }
        }
        return windSpeed;
    }
}
