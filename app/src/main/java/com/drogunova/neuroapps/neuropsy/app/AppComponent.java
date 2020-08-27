package com.drogunova.neuroapps.neuropsy.app;


import com.drogunova.neuroapps.neuropsy.TenWordsModel;
import com.drogunova.neuroapps.neuropsy.TenWordsPresener;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(TenWordsPresener tenWordsPresener);

    void inject(TenWordsModel tenWordsModel);
}
