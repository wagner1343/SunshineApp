package com.wagnersilvestre.sunshineapp.model.datasource.remote;

import com.wagnersilvestre.sunshineapp.model.OpenWeatherAPI;
import com.wagnersilvestre.sunshineapp.model.entity.Forecast;

/**
 * Created by Wagner Silvestre on 3/19/2018.
 */

public class RemoteForecastRepositoryImpl implements RemoteForecastRepository {
    private OpenWeatherAPI openWeatherAPI;
    private static final String API_KEY = "fae7cfaab487814477e7096adf18783a";

    public RemoteForecastRepositoryImpl(){
        openWeatherAPI = new OpenWeatherAPI(API_KEY);
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
