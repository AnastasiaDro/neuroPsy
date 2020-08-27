package com.drogunova.neuroapps.neuropsy;

import android.util.Log;

import com.drogunova.neuroapps.neuropsy.app.App;
import com.drogunova.neuroapps.neuropsy.room.TenWordsTrial;
import com.drogunova.neuroapps.neuropsy.room.TenWordsTrialDao;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TenWordsRoomApi {

    private static final String TAG = "RoomPresenter";

    private TenWordsTrialDao twTrialDao;

    public TenWordsRoomApi() {twTrialDao = App.getAppDatabase().tenWordsTrialDao();}

    public void putTwTrialData(String name, int trialNumber, int trialVariant, int memoSum){
        TenWordsTrial tenWordsTrial = new TenWordsTrial();
        tenWordsTrial.userName = name;
        tenWordsTrial.trialNumber = trialNumber;
        tenWordsTrial.trialVariant = trialVariant;
        tenWordsTrial.memoSum = memoSum;

        Disposable disposable = twTrialDao.insert(tenWordsTrial).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(id -> {
                    Log.d(TAG, "putTwTrialData: " + id);
                }, throwable -> {
                    Log.d(TAG, "putData: " + throwable);
                });
    }

    public void getData() {
        Disposable disposable = twTrialDao.getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(trials -> {
                    Log.d(TAG, "getData: " + trials + " " + Thread.currentThread().getName());
                }, throwable -> {
                    Log.d(TAG, "getData: " + throwable);
                });
    }


}
