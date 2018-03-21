package com.wagnersilvestre.sunshineapp.model.datasource.local.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.wagnersilvestre.sunshineapp.model.datasource.local.db.entity.ForecastDao;
import com.wagnersilvestre.sunshineapp.model.datasource.local.db.entity.ForecastEntity;

/**
 * Created by Wagner Silvestre on 3/19/2018.
 */

@Database(entities = {ForecastEntity.class}, version = 2, exportSchema = false)
public abstract class ForecastDatabase extends RoomDatabase {
    private static final String NAME = "forecast_database";
    private static ForecastDatabase instance;

    public abstract ForecastDao forecastDao();

    public static ForecastDatabase getInstance(Context context){
        if(instance == null){

            instance = Room.databaseBuilder(context, ForecastDatabase.class, ForecastDatabase.NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
