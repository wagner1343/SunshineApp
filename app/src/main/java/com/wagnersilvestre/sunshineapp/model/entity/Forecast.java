package com.wagnersilvestre.sunshineapp.model.entity;

/**
 * Created by Wagner Silvestre on 3/19/2018.
 */

public interface Forecast {
    long getId();
    String getWeather();
    String getCity();
    double getTemperature();
    long getTime();
    void update(Forecast forecast);
}
