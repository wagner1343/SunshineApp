package com.wagnersilvestre.sunshineapp.model.datasource;


import android.content.Context;

import com.wagnersilvestre.sunshineapp.model.datasource.local.LocalForecastRepository;
import com.wagnersilvestre.sunshineapp.model.datasource.local.LocalForecastRepositoryImpl;
import com.wagnersilvestre.sunshineapp.model.datasource.remote.RemoteForecastRepository;
import com.wagnersilvestre.sunshineapp.model.datasource.remote.RemoteForecastRepositoryImpl;
import com.wagnersilvestre.sunshineapp.model.entity.Forecast;

import java.util.List;

/**
 * Created by Wagner Silvestre on 3/19/2018.
 */

public class ForecastRepository {
    LocalForecastRepository lfr;
    RemoteForecastRepository rfr;

    public ForecastRepository(Context context) {
        lfr = new LocalForecastRepositoryImpl(context);
        rfr = new RemoteForecastRepositoryImpl();
    }

    public Forecast getForecastRemoteData(String city){
        return rfr.getCurrentForecastByCityName(city);
    }

    public List<Forecast> getAllForecast(){
        return lfr.getAllForecasts();
    }

    public void deleteAllForecasts(){
        lfr.deleteAllForecasts();
    }

    public void updateForecast(Forecast forecast){
        lfr.updateForecast(forecast);
    }

    public Forecast getWeatherByCityLocal(String currentCityName) {
        return lfr.getCurrentForecastByCityName(currentCityName);
    }

    public void saveForecast(Forecast forecast){
        lfr.saveForecast(forecast);
    }


}
