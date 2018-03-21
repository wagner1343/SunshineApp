package com.wagnersilvestre.sunshineapp.model.datasource.local;

import android.content.Context;

import com.wagnersilvestre.sunshineapp.model.datasource.local.db.ForecastDatabase;
import com.wagnersilvestre.sunshineapp.model.datasource.local.db.entity.ForecastEntity;
import com.wagnersilvestre.sunshineapp.model.entity.Forecast;

import java.util.List;

/**
 * Created by Wagner Silvestre on 3/19/2018.
 */

public class LocalForecastRepositoryImpl implements LocalForecastRepository {
    ForecastDatabase forecastDatabase;

    public LocalForecastRepositoryImpl(Context context){
        this.forecastDatabase = ForecastDatabase.getInstance(context);
    }

    @Override
    public Forecast getCurrentForecastByCityName(String cityName) {
        return forecastDatabase.forecastDao().getForecastByCity(cityName);
    }

    @Override
    public List getAllForecasts() {
        return forecastDatabase.forecastDao().getAllForecast();
    }

    @Override
    public Forecast getLastForecastForCity(String city) {
        return forecastDatabase.forecastDao().getLastForecastForCity(city);
    }

    @Override
    public void saveForecast(Forecast forecast) {
        forecastDatabase.forecastDao().insertForecast(new ForecastEntity(forecast));
    }
}
