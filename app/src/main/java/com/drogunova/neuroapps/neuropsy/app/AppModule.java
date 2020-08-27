package com.drogunova.neuroapps.neuropsy.app;

import android.app.Application;

import com.drogunova.neuroapps.neuropsy.TenWordsModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    TenWordsModel provideTenWordsModel() {return new TenWordsModel(); }
}
