package com.wagnersilvestre.sunshineapp.model.datasource.local.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.wagnersilvestre.sunshineapp.model.entity.Forecast;

/**
 * Created by Wagner Silvestre on 3/19/2018.
 */

@Entity(tableName = "forecast")
public class ForecastEntity implements Forecast {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "weather")
    private String weather;

    @ColumnInfo(name = "temperature")
    private double temperature;

    @ColumnInfo(name = "city")
    private String city;

    @ColumnInfo(name = "date")
    private long time;

    ForecastEntity(String city, String weather, double temperature, long time){
        this.time = time;
        this.weather = weather;
        this.temperature = temperature;
        this.city = city;
    }

    public ForecastEntity(Forecast forecast){
        this.weather = forecast.getWeather();
        this.temperature = forecast.getTemperature();
        this.city = forecast.getCity();
        this.time = forecast.getTime();
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
