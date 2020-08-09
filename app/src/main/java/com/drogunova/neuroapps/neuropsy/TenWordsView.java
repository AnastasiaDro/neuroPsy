package com.drogunova.neuroapps.neuropsy;

import android.view.View;
import android.widget.TextView;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface TenWordsView extends MvpView {
    @StateStrategyType (value = AddToEndSingleStrategy.class)
    void setWordsNamesToTextViews();

}
