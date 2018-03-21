package com.wagnersilvestre.sunshineapp.model.datasource.remote;

import com.wagnersilvestre.sunshineapp.model.entity.Forecast;

/**
 * Created by Wagner Silvestre on 3/19/2018.
 */

public interface RemoteForecastRepository {
    Forecast getCurrentForecastByCityName(String cityName);
}
