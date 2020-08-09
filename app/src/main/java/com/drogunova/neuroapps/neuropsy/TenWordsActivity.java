package com.drogunova.neuroapps.neuropsy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import io.reactivex.Observable;
import io.reactivex.Single;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class TenWordsActivity extends MvpAppCompatActivity implements TenWordsView {

    private static final String TAG = "TenWordsActivity";

    //массив 10 слов из списка (из строкового ресурса)
    TextView [] wordNamesTextViews;
    ArrayList <String> wordsNamesStringsList;
    private Single<String> single;

    @InjectPresenter
    TenWordsPresener tenWordsPresener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_ten_words);
        TextView [] wordNamestextViews = new TextView[10];
    }

    //добавляет слова в
    //массив 10 слов из списка (из строкового ресурса)
    @Override
    public void addWordsFromRecourse(View view) {
        ArrayList <String> wordsNamesStringsList = (ArrayList<String>) Arrays
                .asList(getResources().getStringArray(R.array.tenWords_firstWordSet_array));
    }

    public void setWordsNamesToTextViews(){
        Observable <String>observable = Observable.fromIterable()
    }

}