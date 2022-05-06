package com.example.weatherapplication.service;

import com.example.weatherapplication.MET.Details;
import com.example.weatherapplication.MET.Timeseries;
import com.example.weatherapplication.MET.METRestTemplate;
import com.example.weatherapplication.SMHI.Parameter;
import com.example.weatherapplication.SMHI.SMHI;
import com.example.weatherapplication.SMHI.SMHIRestTemplate;
import com.example.weatherapplication.SMHI.TimeSeries;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    private SMHIRestTemplate smhiRestTemplate;
    private METRestTemplate metRestTemplate;

    private LocalDateTime dateTime = LocalDateTime.now().plusDays(1L);

    private LocalDateTime localDateTime = LocalDateTime.now().plusDays(1L);

    private DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
    private String formattedDateTime = localDateTime.format(formatter);
    private String f = formattedDateTime.substring(0, 10);


    public Timeseries getMETTime() {
        List<Timeseries> timeseries = metRestTemplate.getMETRestTemplate().getProperties().getTimeseries();
        Timeseries t = new Timeseries();

        for (Timeseries time : timeseries) {
            if (time.getTime().substring(0, 10).equals(f)) {
                t = time;
            }
        }
        return t;
    }

    public TimeSeries getSMHITIme() {
        List<TimeSeries> timeSeries = smhiRestTemplate.getSMHIRestTemplate().getTimeSeries();
        TimeSeries t = new TimeSeries();

        for (TimeSeries time : timeSeries) {
            if (time.getValidTime().substring(0, 10).equals("f")) {
                t = time;
            }
        }
        return t;
    }

    public void getWeather() {
        Timeseries METTime = getMETTime();
        TimeSeries SMHITIme = getSMHITIme();

        Double temp;
        temp = METTime.getData().getInstant().getDetails().getAirTemperature();
        Double humidity;
        humidity = METTime.getData().getInstant().getDetails().getRelativeHumidity();
        // r fukt
        // t tempratur

        Double temprature;
        Double fukt;
        Integer metPoints = 0;
        Integer smhiPoints = 0;

        for (Parameter p : SMHITIme.getParameters()) {
            if (p.getName().equals("t")) {
                temprature = p.getValues().get(0);
            } else if (p.getName().equals("r")) {
                fukt = p.getValues().get(0);
            }
        }
    }
}
