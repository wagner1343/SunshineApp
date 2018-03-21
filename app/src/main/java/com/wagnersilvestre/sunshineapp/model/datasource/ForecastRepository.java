package com.wagnersilvestre.sunshineapp.model.datasource;


import com.wagnersilvestre.sunshineapp.model.datasource.local.LocalForecastRepository;
import com.wagnersilvestre.sunshineapp.model.datasource.remote.RemoteForecastRepository;
import com.wagnersilvestre.sunshineapp.model.entity.Forecast;
/**
 * Created by Wagner Silvestre on 3/19/2018.
 */

public class ForecastRepository {
    LocalForecastRepository lfr;
    RemoteForecastRepository rfr;

    public ForecastRepository(LocalForecastRepository lfr, RemoteForecastRepository rfr){
        this.lfr = lfr;
        this.rfr = rfr;
    }

    public Forecast getForecastUpdateByCity(String city){
        return rfr.getCurrentForecastByCityName(city);
    }


    public Forecast getWeatherByCityLocal(String currentCityName) {
        return lfr.getLastForecastForCity(currentCityName);
    }

    public void saveForecast(Forecast forecast){
        lfr.saveForecast(forecast);
    }
}
