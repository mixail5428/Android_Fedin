package com.example.lesson_8_fedin.localDatabase;

import android.app.Application;

import androidx.room.Room;

public class LocalDatabase extends Application {

    private static LocalDatabase instance;
    private AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,
                "basa").build();
    }


    public static LocalDatabase getInstance(){
        return instance;
    }

    public AppDatabase getAppDatabase(){
        return appDatabase;
    }
}
