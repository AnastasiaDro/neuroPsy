package com.drogunova.neuroapps.neuropsy;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class TenWordsActivity extends MvpAppCompatActivity implements TenWordsView {

    private static final String TAG = "TenWordsActivity";
    private static final String WORD_CLICK_TAG = "Pressed Word";

    //массив 10 слов из списка (из строкового ресурса)
    List<String> wordsNamesStringsList;
    private Observable<String> observable;
    TextView [] wordNamesTextViews;
    TextView [] counterTextViews;
    private Disposable disposable;

    @InjectPresenter
    TenWordsPresener tenWordsPresener;

    @ProvidePresenter
    TenWordsPresener providePresenter(){
        TenWordsPresener tenWordsPresener = new TenWordsPresener();
        tenWordsPresener
                .setWordsNamesStringsList(Arrays.asList(getResources().getStringArray(R.array.tenWords_firstWordSet_array)));
        return tenWordsPresener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_ten_words);
        initTextViews();
        //загрузим слова
        tenWordsPresener.setNamesToTextViews();
    }


    @Override
    public void setWordsNamesToTextViews(){
        findTextViewsToArr();
        observable = tenWordsPresener.getObs();
        wordsNamesStringsList = tenWordsPresener.getWordsNamesStringsList();
        disposable = observable.observeOn(AndroidSchedulers.mainThread()).subscribe(s -> {
            wordNamesTextViews[wordsNamesStringsList.indexOf(s)].setText(s);
            Log.d(TAG, "setWordsNamesToTextViews in Thread: " + Thread.currentThread().getName());
        }, throwable -> {
            Log.d(TAG, "setWordsNamesToTextViews() onError");
        }, ()-> {
            Log.d(TAG, "setWordsNamesToTextViews() onComplete");
                }
                );
    }

    @Override
    public void setCounterToWord(int i, String counter) {
        counterTextViews[i].setText(counter);
    }

    //засунем все TextView (названия слов и textView-счетчики в массивы);
    private void findTextViewsToArr() {
        wordNamesTextViews[0] = findViewById(R.id.first_word_name_textView);
        wordNamesTextViews[1] = findViewById(R.id.second_word_name_textView);
        wordNamesTextViews[2] = findViewById(R.id.third_word_name_textView);
        wordNamesTextViews[3] = findViewById(R.id.fourth_word_name_textView);
        wordNamesTextViews[4] = findViewById(R.id.fifth_word_name_textView);
        wordNamesTextViews[5] = findViewById(R.id.sixth_word_name_textView);
        wordNamesTextViews[6] = findViewById(R.id.seventh_word_name_textView);
        wordNamesTextViews[7] = findViewById(R.id.eight_word_name_textView);
        wordNamesTextViews[8] = findViewById(R.id.nine_word_name_textView);
        wordNamesTextViews[9] = findViewById(R.id.ten_word_name_textView);

        counterTextViews[0] = findViewById(R.id.first_word_number_textView);
        counterTextViews[1] = findViewById(R.id.second_word_number_textView);
        counterTextViews[2] = findViewById(R.id.third_word_number_textView);
        counterTextViews[3] = findViewById(R.id.fourth_word_number_textView);
        counterTextViews[4] = findViewById(R.id.fifth_word_number_textView);
        counterTextViews[5] = findViewById(R.id.sixth_word_number_textView);
        counterTextViews[6] = findViewById(R.id.seventh_word_number_textView);
        counterTextViews[7] = findViewById(R.id.eight_word_number_textView);
        counterTextViews[8] = findViewById(R.id.nine_word_number_textView);
        counterTextViews[9] = findViewById(R.id.ten_word_number_textView);
    }

    //обработка нажатия на textView-счетчик
    public void onWordClick(View view) {
        Log.d(WORD_CLICK_TAG, "Слово нажато");
        tenWordsPresener.onWordClick(view, counterTextViews);
    }

    //подготовим TextView
    public void initTextViews(){
        wordNamesTextViews = new TextView[10];
        counterTextViews = new TextView[10];
        findTextViewsToArr();
    }

}