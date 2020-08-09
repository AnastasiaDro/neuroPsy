package com.drogunova.neuroapps.neuropsy;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class TenWordsPresener extends MvpPresenter<TenWordsView> {
    private static final String TAG = "TenWordsPresenter";

    List<String> wordsNamesStringsList;


    public List <String> takeWords(Context context) {
        wordsNamesStringsList = new ArrayList<>();
        wordsNamesStringsList = Arrays.asList(context.getResources().getStringArray(R.array.tenWords_firstWordSet_array));
        return wordsNamesStringsList;
    }

    public Observable<String> getObs(List wordsNamesStringsList) {
        return (Observable<String>) Observable.fromIterable(wordsNamesStringsList).subscribeOn(Schedulers.io());
    }

}
