package com.wagnersilvestre.sunshineapp.model.task;

import android.os.AsyncTask;

import com.wagnersilvestre.sunshineapp.model.datasource.ForecastRepository;
import com.wagnersilvestre.sunshineapp.model.entity.Forecast;
import com.wagnersilvestre.sunshineapp.ui.view.ForecastViewPresenter;

/**
 * Created by Wagner Silvestre on 3/20/2018.
 */

public class FetchForecastFromLocal extends AsyncTask<Integer,Integer,Integer> {

    ForecastViewPresenter presenter;
    ForecastRepository repository;
    Forecast forecast;
    String city;

    public FetchForecastFromLocal(ForecastViewPresenter presenter, ForecastRepository repository, String city){
        this.city = city;
        this.repository = repository;
        this.presenter = presenter;
    }

    @Override
    protected void onPreExecute(){
        presenter.setLoading();
    }

    @Override
    protected Integer doInBackground(Integer... integers) {
        forecast = repository.getWeatherByCityLocal(city);
        return null;
    }

    @Override
    protected void onPostExecute(Integer integer){
        if(forecast != null){
            presenter.setForecastData(forecast);
        }else{
            System.out.println("No forecast on db for " + city);
        }
        presenter.unsetLoading();
    }
}
