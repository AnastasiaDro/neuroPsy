package com.drogunova.neuroapps.neuropsy;

import android.util.Log;

public class TenWordsModel {
    int currentNum;

    private static final String TAG = "TenWordsModel";

    public TenWordsModel() {
        currentNum = 1;
    }

    public int getCurrentNum() {
        return currentNum;
    }

    public void setCurrentNum(int currentNum) {
        this.currentNum = currentNum;
    }




    public void save() {
        Log.d(TAG, "save");
    }
}
