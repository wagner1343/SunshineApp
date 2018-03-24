package com.wagnersilvestre.sunshineapp.model.datasource.local.db.entity;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


/**
 * Created by Wagner Silvestre on 3/19/2018.
 */

@Dao
public interface ForecastDao {
    @Query("SELECT * FROM forecast WHERE city LIKE :city LIMIT 1")
    ForecastEntity getForecastByCity(String city);

    @Query("SELECT * FROM forecast")
    List<ForecastEntity> getAllForecast();

    @Query("SELECT * FROM forecast WHERE city LIKE :city ORDER BY date DESC LIMIT 1")
    ForecastEntity getLastForecastForCity(String city);

    @Delete
    void deleteForecast(ForecastEntity forecastEntity);

    @Update
    void updateForecast(ForecastEntity forecastEntity);

    @Insert
    long insertForecast(ForecastEntity forecastEntity);

    @Query("DELETE FROM forecast")
    void deleteAll();
}
