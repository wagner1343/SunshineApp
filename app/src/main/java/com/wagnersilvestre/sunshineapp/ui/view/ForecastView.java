package com.wagnersilvestre.sunshineapp.ui.view;

import android.arch.lifecycle.Observer;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.wagnersilvestre.sunshineapp.R;
import com.wagnersilvestre.sunshineapp.model.entity.Forecast;

import java.text.SimpleDateFormat;

/**
 * Created by Wagner Silvestre on 3/20/2018.
 */

public class ForecastView extends AppCompatActivity implements Observer<ForecastViewModel> {
    TextView textViewCityName;
    TextView textViewWeather;
    TextView textViewTemperature;
    EditText editTextCityName;
    TextView textViewDate;
    ProgressBar progressBar;
    Button buttonGetWeather;
    ConstraintLayout layout;

    ForecastViewModel viewModel;
    private String currentCityName;

    @Override
    protected void onCreate(Bundle savedBundle){
        super.onCreate(savedBundle);
        setContentView(R.layout.view_forecast);

        getSupportActionBar().setTitle("Weather App");
        getSupportActionBar().setCustomView(R.layout.bar);

        editTextCityName = findViewById(R.id.editTextCityName);
        textViewCityName = findViewById(R.id.textViewCityName);
        textViewWeather = findViewById(R.id.textViewWeather);
        textViewTemperature = findViewById(R.id.textViewTemperature);
        textViewDate = findViewById(R.id.textViewDate);
        progressBar = findViewById(R.id.progressBar);
        buttonGetWeather = findViewById(R.id.buttonGetWeather);
        layout = findViewById(R.id.layout);

        viewModel = new ForecastViewModel(this);
    }


    public void setLoading(boolean isLoading) {
        if(isLoading) {
            progressBar.setVisibility(View.VISIBLE);
            textViewCityName.setVisibility(View.INVISIBLE);
            textViewWeather.setVisibility(View.INVISIBLE);
            textViewTemperature.setVisibility(View.INVISIBLE);
            textViewDate.setVisibility(View.INVISIBLE);
        }
        else {
            progressBar.setVisibility(View.GONE);
            textViewCityName.setVisibility(View.VISIBLE);
            textViewWeather.setVisibility(View.VISIBLE);
            textViewTemperature.setVisibility(View.VISIBLE);
            textViewDate.setVisibility(View.VISIBLE);
        }
    }


    public void setForecastData(Forecast forecast) {
        if(forecast != null) {

            setStyle(forecast.getWeather());

            textViewCityName.setText(forecast.getCity());
            textViewWeather.setText(forecast.getWeather());
            textViewTemperature.setText(String.format("%.0f Cº", forecast.getTemperature() - 273.15));
            textViewDate.setText(new SimpleDateFormat("'updated at' HH:mm dd/MM/yy")
                    .format(forecast.getTime()*1000));
        }
    }

    public void setStyle(String style){
        int pri;
        int sec;
        int ter;

        switch (style){
            case "Rain":
                pri = getResources().getColor(R.color.darkGrey);
                sec = getResources().getColor(R.color.darkBlue);
                ter = getResources().getColor(R.color.lightWhite);
                break;

            case "Clear":
                pri = getResources().getColor(R.color.lightWhite);
                sec = getResources().getColor(R.color.lightBlue);
                ter = getResources().getColor(R.color.black);
                break;

            case "Clouds":
                pri = getResources().getColor(R.color.lightWhite);
                sec = getResources().getColor(R.color.grey);
                ter = getResources().getColor(R.color.black);
                break;

            default:
                pri = getResources().getColor(R.color.black);
                sec = getResources().getColor(R.color.lightWhite);
                ter = getResources().getColor(R.color.black);
                break;
        }

        textViewCityName.setTextColor(pri);
        textViewWeather.setTextColor(pri);
        textViewTemperature.setTextColor(pri);
        textViewDate.setTextColor(pri);
        textViewCityName.setBackgroundColor(sec);
        textViewWeather.setBackgroundColor(sec);
        textViewTemperature.setBackgroundColor(sec);
        textViewDate.setBackgroundColor(sec);

        layout.setBackgroundColor(sec);

        editTextCityName.setTextColor(ter);
        editTextCityName.getBackground().setColorFilter(ter,
                PorterDuff.Mode.SRC_IN);

        buttonGetWeather.setBackgroundColor(pri);
        buttonGetWeather.setTextColor(ter);
    }

    public String getCurrentCityName(){
        return currentCityName;
    }

    public void setCurrentCityName(String cityName){
        this.currentCityName = cityName;
    }

    public void buttonGetWeatherOnClick(View v){
        if(editTextCityName.getText().toString().equals("")){
            Toast.makeText(this.getApplicationContext(), "Type a city name to look for", Toast.LENGTH_SHORT).show();

            return;
        }
        setCurrentCityName(editTextCityName.getText().toString());

        final String city = getCurrentCityName();
        setLoading(true);
        viewModel.getForecastForCity(city).observe(
                this,
                new Observer<Forecast>() {
                    @Override
                    public void onChanged(@Nullable Forecast forecast) {
                        System.out.println("Got cached result for city " + city);
                        setForecastData(forecast);

                        if(forecast != null)
                            setLoading(false);
                        viewModel.updateForecastDataForCity(city).observe(
                                ForecastView.this,
                                new Observer() {
                                    @Override
                                    public void onChanged(@Nullable Object o) {
                                        System.out.println("Got result from server for city " + city);
                                        setLoading(false);
                                        if(getCurrentCityName().equals(city)){
                                            viewModel.getForecastForCity(city).observe(
                                                    ForecastView.this,
                                                    new Observer<Forecast>() {
                                                        @Override
                                                        public void onChanged(@Nullable Forecast forecast) {
                                                            System.out.println("Fetched updated result from db");
                                                            if(forecast == null)
                                                                Toast.makeText(ForecastView.this.getApplicationContext(), "Unable to get "
                                                                        + city + " weather information", Toast.LENGTH_SHORT).show();
                                                            setForecastData(forecast);
                                                        }
                                                    }
                                            );
                                        }
                                    }
                                }
                        );
                    }
                }
        );
    }

    @Override
    public void onChanged(@Nullable ForecastViewModel forecastViewModel) {
    }

}

enum Style{
    CLOUDS,
    RAIN,
    OPENSKY
}
