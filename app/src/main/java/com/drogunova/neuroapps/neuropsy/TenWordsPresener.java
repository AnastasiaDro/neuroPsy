package com.drogunova.neuroapps.neuropsy;

import android.content.Context;
import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class TenWordsPresener extends MvpPresenter<TenWordsView> {
    private static final String TAG = "TenWordsPresenter";

    public List<String> getWordsNamesStringsList() {
        return wordsNamesStringsList;
    }

    List<String> wordsNamesStringsList;

    public void setWordsNamesStringsList(List<String> wordsNamesStringsList) {
        this.wordsNamesStringsList = wordsNamesStringsList;
    }


    public Observable<String> getObs() {

        Observable <String> observable = Observable.fromIterable(wordsNamesStringsList);

        observable.subscribeOn(Schedulers.io());
//        Observable<String> observable = Observable.create((ObservableOnSubscribe<String>) emitter -> {
//            Log.d(TAG, "Поток: " + Thread.currentThread().getName());
//            for (int i = 0; i < wordsNamesStringsList.size(); i++) {
//                String s = wordsNamesStringsList.get(i);
//                emitter.onNext(s);
//            }
//            emitter.onComplete();
//        }).subscribeOn(Schedulers.io());

        return observable;
    }

    public void setNamesToTextViews(){
        getViewState().setWordsNamesToTextViews();
    }

}
