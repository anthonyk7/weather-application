package com.example.weatherapplication;


public class OurWeather {
    private String origin;
    private String timeStamp;
    private Double temperature;
    private Double windSpeed;
    private Double relativeHumidity;

    public OurWeather(String origin, String timeStamp, Double temperature, Double windSpeed, Double relativeHumidity) {
        this.origin = origin;
        this.timeStamp = timeStamp;
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.relativeHumidity = relativeHumidity;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Double getRelativeHumidity() {
        return relativeHumidity;
    }

    public void setRelativeHumidity(Double relativeHumidity) {
        this.relativeHumidity = relativeHumidity;
    }

    @Override
    public String toString() {
        return "BestWeather{" +
                "origin='" + origin + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", temperature=" + temperature +
                ", windSpeed=" + windSpeed +
                ", relativeHumidity=" + relativeHumidity +
                '}';
    }
}
