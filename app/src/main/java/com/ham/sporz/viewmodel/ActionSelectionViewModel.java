package com.ham.sporz.viewmodel;

import android.arch.lifecycle.MutableLiveData;

import com.ham.sporz.viewmodel.enums.Background;

public class ActionSelectionViewModel {
    private MutableLiveData<Background> mMainBackground = new MutableLiveData<>();
    private MutableLiveData<Background> mKillBackground = new MutableLiveData<>();
    private MutableLiveData<Background> mParalyseBackground = new MutableLiveData<>();
    private MutableLiveData<Background> mInfectBackground = new MutableLiveData<>();

    private boolean isMainSelected = false;
    private boolean isKillSelected = false;
    private boolean isParalyseSelected = false;
    private boolean isInfectSelected = false;

    private int mPlayerId;
    private MultipleSelectionViewModel mParentVM;

    public ActionSelectionViewModel(MultipleSelectionViewModel parentVM, int playerId){
        mMainBackground.setValue(Background.NORMAL);
        mKillBackground.setValue(Background.NORMAL);
        mParalyseBackground.setValue(Background.NORMAL);
        mInfectBackground.setValue(Background.NORMAL);
        mParentVM = parentVM;
        mPlayerId = playerId;
    }

    public boolean showSecondaryActions(){
        return true;
    }

    public void onMainClick(){

    }
    public void onKillClick(){

    }
    public void onParalyseClick(){

    }
    public void onInfectClick(){

    }

    public void onValidate(){

    }

    public void onCancel(){    }

    public MutableLiveData<Background> getMainBackground(){
        return mMainBackground;
    }

    public MutableLiveData<Background> getKillBackground() {
        return mKillBackground;
    }

    public MutableLiveData<Background> getParalyseBackground() {
        return mParalyseBackground;
    }

    public MutableLiveData<Background> getInfectBackground() {
        return mInfectBackground;
    }
}
