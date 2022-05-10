package com.example.weatherapplication.MET;

import com.example.weatherapplication.OurWeather;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.WebServiceClient;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class MetDao {

    private final String URL = "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat=59.3110&lon=18.0300";
    private RestTemplate rt = new RestTemplateBuilder()
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader(HttpHeaders.USER_AGENT, "notJava!")
            .build();

    public OurWeather getTomorrowsWeatherObject(){
        String tomorrowsDateAndTime = LocalDateTime.now()
                .plusDays(1L)
                .format(DateTimeFormatter.ISO_DATE_TIME)
                .substring(0, 13);

        MET allMetData = rt.getForObject(URL, MET.class);
        List<Timeseries> timeseries = allMetData.getProperties().getTimeseries();
        Timeseries t = new Timeseries();

        for (Timeseries time : timeseries) {
            if (time.getTime().substring(0, 13).equals(tomorrowsDateAndTime)) {
                t = time;
            }
        }
        return new OurWeather("Met",
                t.getTime(),
                t.getData().getInstant().getDetails().getAirTemperature(),
                t.getData().getInstant().getDetails().getWindSpeed(),
                t.getData().getInstant().getDetails().getRelativeHumidity());
    }
}
