package com.example.weatherapplication.MET;

import com.example.weatherapplication.MET.MET;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class METRestTemplate {

    private RestTemplate rt = new RestTemplateBuilder().defaultHeader(HttpHeaders.ACCEPT,
            MediaType.APPLICATION_JSON_VALUE).defaultHeader(HttpHeaders.USER_AGENT, "notJava!").build();

    private String MET_URL = "https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/18.0300/lat/59.3110/data.json";


    public MET getMETRestTemplate() {
        return rt.getForObject(MET_URL, MET.class);
    }


}
