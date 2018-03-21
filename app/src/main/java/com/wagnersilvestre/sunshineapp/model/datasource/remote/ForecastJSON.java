package com.wagnersilvestre.sunshineapp.model.datasource.remote;

import com.wagnersilvestre.sunshineapp.model.entity.Forecast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Wagner Silvestre on 3/19/2018.
 */


public class ForecastJSON implements Forecast {
    private long id;

    private String weather;

    private double temperature;

    private String city;

    private long time;

    ForecastJSON(String JSONString){
        JSONObject forecastJSON = null;
        try {
            forecastJSON = new JSONObject(JSONString);
            this.weather = forecastJSON.getJSONArray("weather").getJSONObject(0).getString("main");
            this.temperature = forecastJSON.getJSONObject("main").getDouble("temp");
            this.city = forecastJSON.getString("name");
            this.time = forecastJSON.getLong("dt");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    ForecastJSON(Forecast forecast){
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