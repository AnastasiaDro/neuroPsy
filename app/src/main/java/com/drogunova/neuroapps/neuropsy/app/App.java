package com.drogunova.neuroapps.neuropsy.app;

import android.app.Application;

public class App extends Application {
    private static AppComponent AppComponent;

    @Override
    public void onCreate(){
        super.onCreate();
        AppComponent = generateAppComponent();
    }

    public static AppComponent getAppComponent() { return  AppComponent; }

    public AppComponent generateAppComponent() {
        return DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }
}
