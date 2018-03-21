package com.wagnersilvestre.sunshineapp.model.datasource.remote;

import com.wagnersilvestre.sunshineapp.model.OpenWeatherAPI;
import com.wagnersilvestre.sunshineapp.model.entity.Forecast;

/**
 * Created by Wagner Silvestre on 3/19/2018.
 */

public class RemoteForecastRepositoryImpl implements RemoteForecastRepository{
    OpenWeatherAPI openWeatherAPI;

    public RemoteForecastRepositoryImpl(String key){
        openWeatherAPI = new OpenWeatherAPI(key);
    }

    @Override
    public Forecast getCurrentForecastByCityName(String cityName) {
        String response = openWeatherAPI.getCurrentWeatherByName(cityName);
        if(response != null){
           return new ForecastJSON(response);
        }
        else{
            return null;
        }
    }
}
