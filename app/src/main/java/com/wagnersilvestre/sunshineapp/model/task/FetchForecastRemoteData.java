package com.wagnersilvestre.sunshineapp.model.task;

import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.wagnersilvestre.sunshineapp.model.datasource.ForecastRepository;
import com.wagnersilvestre.sunshineapp.model.entity.Forecast;

/**
 * Created by Wagner Silvestre on 3/20/2018.
 */

public class FetchForecastRemoteData extends AsyncTask<Integer, Integer, Integer>{

    MutableLiveData observer;
    ForecastRepository repository;
    String city;

    public FetchForecastRemoteData(MutableLiveData observer, ForecastRepository repository, String city){
        this.city = city;
        this.repository = repository;
        this.observer = observer;
    }


    @Override
    protected Integer doInBackground(Integer... integers) {
        Forecast cachedForecast = repository.getWeatherByCityLocal(city);
        Forecast updatedForecast = repository.getForecastRemoteData(city);

        if(cachedForecast != null) {

            if(updatedForecast != null){
                cachedForecast.update(updatedForecast);
                repository.updateForecast(cachedForecast);
                return 1;
            }
            else
                return 0;

        }
        else{
            if(updatedForecast != null){
                repository.saveForecast(updatedForecast);
                return 1;
            }
            else
                return 0;
        }
    }

    @Override
    protected void onPostExecute(Integer result){
        observer.setValue(result);
    }
}
