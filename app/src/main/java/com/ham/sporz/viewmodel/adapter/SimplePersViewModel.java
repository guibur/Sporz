package com.ham.sporz.viewmodel.adapter;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.ham.sporz.model.Player;
import com.ham.sporz.viewmodel.enums.Background;
import com.ham.sporz.viewmodel.enums.Symbol;

public class SimplePersViewModel extends ViewModel implements CardViewModel {
    private Player mPlayer;
    private MutableLiveData<Symbol> mSymbolToShow = new MutableLiveData<>();
    private MutableLiveData<Background> mBackground = new MutableLiveData<>();
    private boolean mInactiveFilter = false;

    public SimplePersViewModel(Player player) {
        mPlayer = player;
        mSymbolToShow.setValue(Symbol.NONE);
        mBackground.setValue(Background.NORMAL);
    }

    public int getId(){
        return mPlayer.getId();
    }

    public String getName(){
        return mPlayer.getName();
    }

    public boolean isClickable(){
        return mPlayer.isAlive();
    }

    public void setBackground(Background background){
        mBackground.setValue(background);
    }

    public MutableLiveData<Background> getBackground() {
        return mBackground;
    }

    public void setSymbolToShow(Symbol symbol){
        mSymbolToShow.setValue(symbol);
    }

    public MutableLiveData<Symbol> getSymbol(){
        return mSymbolToShow;
    }

    public void setInactiveFilter(boolean inactiveFilter){
        mInactiveFilter = inactiveFilter;
    }

    public boolean showInactiveFilter() {
        return mInactiveFilter;
    }
}
