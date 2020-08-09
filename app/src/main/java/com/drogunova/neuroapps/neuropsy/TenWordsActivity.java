package com.drogunova.neuroapps.neuropsy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class TenWordsActivity extends MvpAppCompatActivity implements TenWordsView {

    private static final String TAG = "TenWordsActivity";

    //массив 10 слов из списка (из строкового ресурса)
    List<String> wordsNamesStringsList;
    private Observable<String> observable;
    TextView [] wordNamestextViews;

    @InjectPresenter
    TenWordsPresener tenWordsPresener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_ten_words);
        TextView [] wordNamestextViews = new TextView[10];
        findTextViewsToArr(wordNamestextViews);
        //загрузим слова
        tenWordsPresener.loadWords(this);
    }


    @Override
    public void setWordsNamesToTextViews(){
        wordNamestextViews = new TextView[10];
        findTextViewsToArr(wordNamestextViews);
        observable = tenWordsPresener.loadWords(this);
        Disposable disposable = observable.observeOn(AndroidSchedulers.mainThread()).subscribe(s -> {
            wordNamestextViews[wordsNamesStringsList.indexOf(s)].setText(s);
            Log.d(TAG, "setWordsNamesToTextViews in Thread: " + Thread.currentThread().getName());
        });
    }


    private void findTextViewsToArr(TextView [] textViews) {
        textViews[0] = findViewById(R.id.first_word_name_textView);
        textViews[1] = findViewById(R.id.second_word_name_textView);
        textViews[2] = findViewById(R.id.third_word_name_textView);
        textViews[3] = findViewById(R.id.fourth_word_name_textView);
        textViews[4] = findViewById(R.id.fifth_word_name_textView);
        textViews[5] = findViewById(R.id.sixth_word_name_textView);
        textViews[6] = findViewById(R.id.seventh_word_name_textView);
        textViews[7] = findViewById(R.id.eight_word_name_textView);
        textViews[8] = findViewById(R.id.nine_word_name_textView);
        textViews[9] = findViewById(R.id.ten_word_name_textView);
    }

}