package com.wagnersilvestre.sunshineapp.model.task;

import android.os.AsyncTask;

import com.wagnersilvestre.sunshineapp.model.datasource.ForecastRepository;
import com.wagnersilvestre.sunshineapp.model.entity.Forecast;
import com.wagnersilvestre.sunshineapp.ui.view.ForecastViewPresenter;

/**
 * Created by Wagner Silvestre on 3/20/2018.
 */

public class UpdateForecastViewForCityNameRemote extends AsyncTask<Integer, Integer, Integer> {

    ForecastViewPresenter presenter;
    ForecastRepository repository;
    Forecast forecast;
    String city;

    public UpdateForecastViewForCityNameRemote(ForecastViewPresenter presenter, ForecastRepository repository, String city){
        this.city = city;
        this.repository = repository;
        this.presenter = presenter;
    }

    @Override
    protected void onPreExecute(){

    }

    @Override
    protected Integer doInBackground(Integer... integers) {
        System.out.println("Checking for online forecast updates");

        forecast = repository.getForecastUpdateByCity(city);
        if(forecast != null){
            repository.saveForecast(forecast);
            return 1;
        }
        return 0;
    }

    @Override
    protected void onPostExecute(Integer integer){
        if(integer == 1){
            System.out.println("got response for city " + city);
            presenter.forecastUpdated();
        }
        presenter.unsetLoading();

    }
}
