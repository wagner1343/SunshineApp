package com.wagnersilvestre.sunshineapp.ui.view;

import com.wagnersilvestre.sunshineapp.model.datasource.ForecastRepository;
import com.wagnersilvestre.sunshineapp.model.datasource.local.LocalForecastRepository;
import com.wagnersilvestre.sunshineapp.model.datasource.local.LocalForecastRepositoryImpl;
import com.wagnersilvestre.sunshineapp.model.datasource.remote.RemoteForecastRepository;
import com.wagnersilvestre.sunshineapp.model.datasource.remote.RemoteForecastRepositoryImpl;
import com.wagnersilvestre.sunshineapp.model.entity.Forecast;
import com.wagnersilvestre.sunshineapp.model.task.FetchForecastFromLocal;
import com.wagnersilvestre.sunshineapp.model.task.UpdateForecastViewForCityName;

/**
 * Created by Wagner Silvestre on 3/20/2018.
 */

public class ForecastViewPresenter {
    private ForecastRepository repository;
    ForecastView view;

    ForecastViewPresenter(ForecastView view){
        this.view = view;

        LocalForecastRepository localForecastRepository = new LocalForecastRepositoryImpl(view.getApplicationContext());
        RemoteForecastRepository remoteForecastRepository = new RemoteForecastRepositoryImpl("fae7cfaab487814477e7096adf18783a");

        repository = new ForecastRepository(localForecastRepository, remoteForecastRepository);
    }

    public void repositoryUpdated(){
        Forecast forecast = repository.getWeatherByCityLocal(view.getCurrentCityName());
        view.setForecastData(forecast);
    }

    public void getForecastByCityName(String city){
        UpdateForecastViewForCityName task =
                new UpdateForecastViewForCityName(this, repository, city);
        task.execute(1);
    }

    public void forecastUpdated() {
        FetchForecastFromLocal task =
                new FetchForecastFromLocal(this, repository, view.getCurrentCityName());
        task.execute(1);
    }

    public void unsetLoading() {
        view.unsetLoading();
    }

    public void setForecastData(Forecast forecast) {
        view.setForecastData(forecast);
    }

    public void setLoading() {
        view.setLoading();
    }
}
