package com.wagnersilvestre.sunshineapp.ui.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wagnersilvestre.sunshineapp.R;
import com.wagnersilvestre.sunshineapp.model.entity.Forecast;

import java.text.SimpleDateFormat;

/**
 * Created by Wagner Silvestre on 3/20/2018.
 */

public class ForecastView extends Activity{
    TextView textViewLoading;
    TextView textViewCityName;
    TextView textViewWeather;
    TextView textViewTemperature;
    Button buttonGetWeather;
    EditText editTextCityName;
    TextView textViewDate;

    ForecastViewPresenter presenter;

    @Override
    protected void onCreate(Bundle savedBundle){
        super.onCreate(savedBundle);
        setContentView(R.layout.view_forecast);


        textViewCityName = findViewById(R.id.textViewCityName);
        textViewLoading = findViewById(R.id.textViewLoading);
        textViewTemperature = findViewById(R.id.textViewTemperature);
        textViewWeather = findViewById(R.id.textViewWeather);
        buttonGetWeather = findViewById(R.id.buttonGetWeather);
        editTextCityName = findViewById(R.id.editTextCityName);
        textViewDate = findViewById(R.id.textViewDate);

        presenter = new ForecastViewPresenter(this);
    }


    public void setLoading() {
        textViewLoading.setText("Loading...");
    }

    public void unsetLoading(){
        textViewLoading.setText("");
    }

    public void setForecastData(Forecast forecast) {
        if(forecast != null) {
            textViewCityName.setText(forecast.getCity());
            textViewWeather.setText(forecast.getWeather());
            textViewTemperature.setText(String.format("%.0f Cº", forecast.getTemperature() - 273.15));
            textViewDate.setText(new SimpleDateFormat("'updated at' HH:mm dd/MM/yy")
                    .format(forecast.getTime()*1000));
        }
        else{
            textViewCityName.setText("City Name");
            textViewWeather.setText("Weather");
            textViewTemperature.setText(String.format("Temperature"));
            textViewDate.setText("");
        }
    }

    public String getCurrentCityName(){
        return String.valueOf(editTextCityName.getText());
    }

    public void buttonGetWeatherOnClick(View v){
        presenter.getForecastByCityName(getCurrentCityName());
    }
}
