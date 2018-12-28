package com.ham.sporz.viewmodel.adapter;

import android.arch.lifecycle.MutableLiveData;

import com.ham.sporz.viewmodel.enums.Background;
import com.ham.sporz.viewmodel.enums.Symbol;

public interface CardViewModel {
    public boolean isClickable();
    public MutableLiveData<Background> getBackground();
    public boolean showInactiveFilter();
}
