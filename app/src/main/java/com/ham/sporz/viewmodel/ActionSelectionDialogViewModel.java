package com.ham.sporz.viewmodel;

import android.arch.lifecycle.MutableLiveData;

import com.ham.sporz.model.enums.ActionType;
import com.ham.sporz.viewmodel.enums.Background;

public class ActionSelectionDialogViewModel {
    private ActionType mCurrentAction;

    private MutableLiveData<Background> mMainBackground = new MutableLiveData<>();
    private MutableLiveData<Background> mKillBackground = new MutableLiveData<>();
    private MutableLiveData<Background> mParalyseBackground = new MutableLiveData<>();
    private MutableLiveData<Background> mInfectBackground = new MutableLiveData<>();

    private boolean mIsMainSelected = false;
    private boolean mIsKillSelected = false;
    private boolean mIsParalyseSelected = false;
    private boolean mIsInfectSelected = false;

    private boolean isMainActionDepleted;
    private boolean isSecondaryActionDepleted;

    private SelectionWithActionDialog mParentVM;

    public ActionSelectionDialogViewModel(SelectionWithActionDialog parentVM,
                                          ActionType currentAction,
                                          boolean isMainActionDepleted,
                                          boolean isSecondaryActionDepleted,
                                          boolean isMainSelected,
                                          boolean isKillSelected,
                                          boolean isParalyseSelected,
                                          boolean isInfectSelected){
        mCurrentAction = currentAction;
        mParentVM = parentVM;

        mIsMainSelected = isMainSelected;
        mIsKillSelected = isKillSelected;
        mIsParalyseSelected = isParalyseSelected;
        mIsInfectSelected = isInfectSelected;

        if (isMainActionDepleted) { // It is assumed that action cannot be both depleted and chose by the player
            mMainBackground.setValue(Background.DARK);
            mKillBackground.setValue(Background.DARK);
        } else {
            mMainBackground.setValue(isMainSelected ? Background.ACCENT : Background.NORMAL);
            mKillBackground.setValue(isKillSelected ? Background.ACCENT : Background.NORMAL);
        }
        if (isSecondaryActionDepleted) {
            mParalyseBackground.setValue(Background.DARK);
            mInfectBackground.setValue(Background.DARK);
        } else {
            mParalyseBackground.setValue(isParalyseSelected ? Background.ACCENT : Background.NORMAL);
            mInfectBackground.setValue(isInfectSelected ? Background.ACCENT : Background.NORMAL);
        }
    }

    public boolean showSecondaryActions(){
        return true;
    }

    public void onMainClick(){
        mKillBackground.setValue(isMainActionDepleted ? Background.DARK : Background.NORMAL);
        mIsKillSelected = false;
        if (mIsMainSelected){
            mIsMainSelected = false;
            mMainBackground.setValue(isMainActionDepleted ? Background.DARK : Background.NORMAL);
        } else {
            mIsMainSelected = true;
            mMainBackground.setValue(Background.ACCENT);
        }
    }
    public void onKillClick(){
        mMainBackground.setValue(isMainActionDepleted ? Background.DARK : Background.NORMAL);
        mIsMainSelected = false;
        if (mIsKillSelected){
            mIsKillSelected = false;
            mKillBackground.setValue(isMainActionDepleted ? Background.DARK : Background.NORMAL);
        } else {
            mIsKillSelected = true;
            mKillBackground.setValue(Background.ACCENT);
        }
    }
    public void onParalyseClick(){
        mInfectBackground.setValue(isSecondaryActionDepleted ? Background.DARK : Background.NORMAL);
        mIsInfectSelected = false;
        if (mIsParalyseSelected){
            mIsParalyseSelected = false;
            mParalyseBackground.setValue(isSecondaryActionDepleted ? Background.DARK : Background.NORMAL);
        } else {
            mIsParalyseSelected = true;
            mParalyseBackground.setValue(Background.ACCENT);
        }
    }
    public void onInfectClick(){
        mParalyseBackground.setValue(isSecondaryActionDepleted ? Background.DARK : Background.NORMAL);
        mIsParalyseSelected = false;
        if (mIsInfectSelected){
            mIsInfectSelected = false;
            mInfectBackground.setValue(isSecondaryActionDepleted ? Background.DARK : Background.NORMAL);
        } else {
            mIsInfectSelected = true;
            mInfectBackground.setValue(Background.ACCENT);
        }
    }

    public void onValidate(){
        mParentVM.setResultActionSelection(mIsMainSelected, mIsKillSelected,
                mIsParalyseSelected, mIsInfectSelected);
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