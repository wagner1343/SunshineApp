package com.wagnersilvestre.sunshineapp.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Wagner Silvestre on 3/19/2018.
 */


public class OpenWeatherAPI {
    private String key;
    private String hostname;

    public OpenWeatherAPI(String key){
        this.key = key;
        this.hostname = "http://api.openweathermap.org/data/2.5/";
    }

    private String getResponse(String url){
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String response = null;

        try{
            URL urlConnection = new URL(url);
            connection = (HttpURLConnection) urlConnection.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            InputStream in = connection.getInputStream();
            if(in == null){
                return null;
            }

            StringBuffer buffer = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while((line = reader.readLine()) != null){
                buffer.append(line);
                buffer.append("\n");
            }

            if(buffer.length() == 0){
                return null;
            }

            response =  buffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            if(connection != null){
                connection.disconnect();
            }
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("response: " + response);

        return response;
    }

    public String getCurrentWeatherByName(String name){
        String url = String.format("%sweather?q=%s&appid=%s", hostname, name, key);

        return getResponse(url);
    }

    public String getForecastByName(String name){
        String url = String.format("%sforecast?q=%s&appid=%s", hostname, name, key);

        return getResponse(url);
    }

    public String getForecastById(String id){
        String url = String.format("%sforecast?id=%s&appid=%s", hostname, id, key);

        return getResponse(url);
    }

    public String getCurrentWeatherById(String id){
        String url = String.format("%sweather?id=%s&appid=%s", hostname, id, key);

        return getResponse(url);
    }
}
