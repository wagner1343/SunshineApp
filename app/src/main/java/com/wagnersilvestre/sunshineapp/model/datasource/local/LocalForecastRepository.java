package com.wagnersilvestre.sunshineapp.model.datasource.local;

import com.wagnersilvestre.sunshineapp.model.entity.Forecast;

import java.util.List;

/**
 * Created by Wagner Silvestre on 3/19/2018.
 */

public interface LocalForecastRepository {
    Forecast getCurrentForecastByCityName(String cityName);
    List<Forecast> getAllForecasts();
    Forecast getLastForecastForCity(String city);

    void saveForecast(Forecast forecast);
}
