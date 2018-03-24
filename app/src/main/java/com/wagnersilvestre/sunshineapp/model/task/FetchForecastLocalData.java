package com.wagnersilvestre.sunshineapp.model.task;

import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import com.wagnersilvestre.sunshineapp.model.datasource.ForecastRepository;
import com.wagnersilvestre.sunshineapp.model.entity.Forecast;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Wagner Silvestre on 3/20/2018.
 */

public class FetchForecastLocalData extends AsyncTask<Integer,Integer,Forecast> {

    ForecastRepository repository;
    MutableLiveData<Forecast> forecastData;
    String city;
    Forecast result;

    public FetchForecastLocalData(
            MutableLiveData<Forecast> forecastData, ForecastRepository repository, String city){
        this.city = city;
        this.repository = repository;
        this.forecastData = forecastData;
    }

    @Override
    protected void onPreExecute(){
    }

    @Override
    protected Forecast doInBackground(Integer... integers) {

        List<Forecast> forecasts = repository.getAllForecast();

        for(Forecast forecast:forecasts){
            System.out.println(forecast.getId() + ": " + forecast.getCity() + " " + String.format("%.0f Cº", forecast.getTemperature() - 273.15)
            + " " + forecast.getWeather() + " " + new SimpleDateFormat("'updated at' HH:mm dd/MM/yy")
                    .format(forecast.getTime()*1000));
        }

        result = repository.getWeatherByCityLocal(city);
        return null;
    }

    @Override
    protected void onPostExecute(Forecast forecast){
        forecastData.setValue(result);
    }
}
