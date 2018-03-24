package com.wagnersilvestre.sunshineapp.ui.view;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;

import com.wagnersilvestre.sunshineapp.model.datasource.ForecastRepository;
import com.wagnersilvestre.sunshineapp.model.entity.Forecast;
import com.wagnersilvestre.sunshineapp.model.task.FetchForecastLocalData;
import com.wagnersilvestre.sunshineapp.model.task.FetchForecastRemoteData;

/**
 * Created by Wagner Silvestre on 3/20/2018.
 */

public class ForecastViewModel  {
    private ForecastRepository repository;

    ForecastViewModel(Context context){
        repository = new ForecastRepository(context);
    }

    public MutableLiveData<Forecast> getForecastForCity(String currentCityName) {
        MutableLiveData<Forecast> forecastData = new MutableLiveData<>();
        FetchForecastLocalData task =
                new FetchForecastLocalData(forecastData, repository, currentCityName);
        task.execute();

        return forecastData;
    }

    public MutableLiveData updateForecastDataForCity(String city){
        MutableLiveData observable = new MutableLiveData();
        FetchForecastRemoteData task =
                new FetchForecastRemoteData(observable, repository, city);
        task.execute();

        return observable;
    }

}
