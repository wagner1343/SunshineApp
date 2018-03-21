package com.wagnersilvestre.sunshineapp.model.entity;

/**
 * Created by Wagner Silvestre on 3/19/2018.
 */

public class ForecastImpl implements  Forecast{
    private long id;
    private String weather;

    private double temperature;

    private String city;
    private long time;

    public ForecastImpl(String city, String weather, double temperature, long time){
        this.time = time;
        this.weather = weather;
        this.temperature = temperature;
        this.city = city;
    }

    ForecastImpl(Forecast forecast){
        this.weather = forecast.getWeather();
        this.temperature = forecast.getTemperature();
        this.city = forecast.getCity();
    }

    public String getWeather() {
        return weather;
    }

    @Override
    public String getCity() {
        return this.city;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public double getTemperature() {
        return temperature;
    }

    @Override
    public long getTime() {
        return this.time;
    }

    @Override
    public void update(Forecast forecast) {
        this.city = forecast.getCity();
        this.temperature = forecast.getTemperature();
        this.weather = forecast.getWeather();
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public long getId() {
        return id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setId(long id) {
        this.id = id;
    }
}
