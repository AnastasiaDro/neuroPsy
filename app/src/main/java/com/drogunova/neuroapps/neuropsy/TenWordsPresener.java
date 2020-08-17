package com.drogunova.neuroapps.neuropsy;

import android.content.Context;
import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class TenWordsPresener extends MvpPresenter<TenWordsView> {
    private static final String TAG = "TenWordsPresenter";

    private int counter;

    public List<String> getWordsNamesStringsList() {
        return wordsNamesStringsList;
    }

    List<String> wordsNamesStringsList;

    //получатель числа
    private Single<Integer> obsSetCounter;

    public void setWordsNamesStringsList(List<String> wordsNamesStringsList) {
        this.wordsNamesStringsList = wordsNamesStringsList;
    }


    public Observable<String> getObs() {
        Observable <String> observable = Observable.fromIterable(wordsNamesStringsList);
        observable.subscribeOn(Schedulers.io());
        return observable;
    }

    public void setNamesToTextViews(){
        getViewState().setWordsNamesToTextViews();
    }

    public int takeCounter(){
        counter++;
        return counter;
    }

    //создаём Observable для поиска идентичного нажатому view
    public Single getObjNumder(Object object, Object[] objects) {
        obsSetCounter = Single.create((SingleOnSubscribe<Integer>) emitter ->
        {
           int u = findU(object, objects);
            emitter.onSuccess(u);
        }).subscribeOn(Schedulers.io());
        return obsSetCounter;
    }

    //найдем идентичную нажатой view
    private int findU(Object object, Object[] objects){
        int u= 0;
        for (int i = 0; i < objects.length; i++) {
            if (object == objects[i]){
                Log.d(TAG, "найдено идентичное вью");
                Log.d(TAG, "Сделано в потоке: " + Thread.currentThread().getName());
                u = i;
                return u;
            } else {
                Log.d(TAG, "ничего не найдено");
            }
        }
        return u;
    }

}
