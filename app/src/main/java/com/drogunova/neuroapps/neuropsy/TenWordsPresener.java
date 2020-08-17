package com.drogunova.neuroapps.neuropsy;

import android.util.Log;
import java.util.List;


import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
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


    //для выгрузки названий слов из списка
    public Observable<String> getObs() {
        Observable <String> observable = Observable.fromIterable(wordsNamesStringsList);
        observable.subscribeOn(Schedulers.io());
        return observable;
    }

    public void setNamesToTextViews(){
        getViewState().setWordsNamesToTextViews();
    }

    //получим следующиый порядковый номер и увеличим его на 1
    public int takeCounter(){
        counter++;
        return counter;
    }

    //2. создаём Observable для поиска идентичного нажатому view
    public Single getObjNumder(Object object, Object[] objects) {
        obsSetCounter = Single.create((SingleOnSubscribe<Integer>) emitter ->
        {
           int u = findU(object, objects);
            emitter.onSuccess(u);
        }).subscribeOn(Schedulers.io());

        return obsSetCounter;
    }

    //3. найдем номер нажатой view в массиве наших вью
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

    //1.(от большего к меньшему) обработаем нажатую кнопку
    public void onWordClick(Object object, Object[]objects) {
        Disposable disposable = getObjNumder(object, objects).observeOn(AndroidSchedulers.mainThread()).subscribe(i ->{
            Log.d(TAG, "наше i равно "+ i);
            //проверим, какой это поток
            Log.d(TAG, Thread.currentThread().getName());
            //вставим в него текст
            int u = takeCounter();
            getViewState().setCounterToWord((Integer) i, Integer.toString(u));
        });
    }


}
