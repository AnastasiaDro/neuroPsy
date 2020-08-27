package com.drogunova.neuroapps.neuropsy.app;

import android.app.Application;

import androidx.room.Room;

import com.drogunova.neuroapps.neuropsy.room.AppDataBase;

public class App extends Application {
    private static AppComponent AppComponent;
    private static AppDataBase appDataBase;

    @Override
    public void onCreate(){
        super.onCreate();
        AppComponent = generateAppComponent();

        appDataBase = Room.databaseBuilder(getApplicationContext(),
                AppDataBase.class, "room_database").build();
    }

    public static AppDataBase getAppDatabase() {
        return appDataBase;
    }
    public static AppComponent getAppComponent() { return  AppComponent; }

    public AppComponent generateAppComponent() {
        return DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }
}
