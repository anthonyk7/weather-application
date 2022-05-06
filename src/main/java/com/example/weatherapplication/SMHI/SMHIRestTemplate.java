package com.example.weatherapplication.SMHI;

import com.example.weatherapplication.SMHI.SMHI;
import org.springframework.web.client.RestTemplate;

public class SMHIRestTemplate {


    private String SMHI_URL = "https://opendata-download-metfcst.smhi.se/api/category/pmp3g/version/2/geotype/point/lon/18.0300/lat/59.3110/data.json";
    private RestTemplate restTemplate = new RestTemplate();


    public SMHI getSMHIRestTemplate() {
        return restTemplate.getForObject(SMHI_URL, SMHI.class);
    }
}
