package com.drogunova.neuroapps.neuropsy.app;

import android.app.Application;

import com.drogunova.neuroapps.neuropsy.TenWordsModel;
import com.drogunova.neuroapps.neuropsy.TenWordsRoomApi;

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

    @Provides
    @Singleton
    TenWordsRoomApi provideTenWordsRoomApi() {return new TenWordsRoomApi();}
}
