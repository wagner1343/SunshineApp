package com.wagnersilvestre.sunshineapp.model.datasource.local;

import com.wagnersilvestre.sunshineapp.model.entity.Forecast;

import java.util.List;

/**
 * Created by Wagner Silvestre on 3/19/2018.
 */

public interface LocalForecastRepository {
    Forecast getCurrentForecastByCityName(String cityName);
    List<Forecast> getAllForecasts();

    void saveForecast(Forecast forecast);

    void deleteAllForecasts();

    void updateForecast(Forecast forecast);
}
