package com.wagnersilvestre.sunshineapp.model.task;

import android.os.AsyncTask;

import com.wagnersilvestre.sunshineapp.model.datasource.ForecastRepository;
import com.wagnersilvestre.sunshineapp.model.entity.Forecast;
import com.wagnersilvestre.sunshineapp.ui.view.ForecastViewPresenter;

/**
 * Created by Wagner Silvestre on 3/20/2018.
 */

public class UpdateForecastViewForCityName extends AsyncTask<Integer, Integer, Integer>{

    ForecastViewPresenter presenter;
    ForecastRepository repository;
    Forecast forecast;
    String city;

    public UpdateForecastViewForCityName(ForecastViewPresenter presenter, ForecastRepository repository, String city){
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
        if (forecast == null) {
            System.out.println("No forecast stored for city " + city);
        }

        presenter.setForecastData(forecast);
        presenter.unsetLoading();

        UpdateForecastViewForCityNameRemote task =
                new UpdateForecastViewForCityNameRemote(presenter, repository, city);
        task.execute(1);
    }
}
