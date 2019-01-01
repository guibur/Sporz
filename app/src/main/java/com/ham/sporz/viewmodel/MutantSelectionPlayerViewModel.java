package com.ham.sporz.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.ham.sporz.utils.SingleLiveEvent;
import com.ham.sporz.viewmodel.enums.Background;
import com.ham.sporz.viewmodel.enums.Symbol;

public class MutantSelectionPlayerViewModel extends AbstractSelectionPlayerViewModel implements SelectionWithActionDialog {
    private String TAG = "MuttantSelectionPlayerVM";

    private SingleLiveEvent<ActionSelectionDialogViewModel> mActionSelectionDialog = new SingleLiveEvent<>();
    private int mCurrentTargetPlayer = -1;
    private int mPlayerFirstAction = -1;
    private boolean isFirstActionMutate = true;
    private int mPlayerSecondAction = -1;
    private boolean isSecondActionParalyse = true;

    @Override
    public MutableLiveData<ActionSelectionDialogViewModel> getActionSelectionDialogVM() {
        return mActionSelectionDialog;
    }

    @Override
    public void selectPlayer(int playerId) {
        mCurrentTargetPlayer = playerId;
        mActionSelectionDialog.setValue(new ActionSelectionDialogViewModel(
                this,
                mCurrentGame.getCurrentTurn().getCurrentAction().getType(),
                (mPlayerFirstAction != playerId && mPlayerFirstAction >= 0),
                (mPlayerSecondAction != playerId && mPlayerSecondAction >= 0),
                (isFirstActionMutate && mPlayerFirstAction == playerId),
                (!isFirstActionMutate && mPlayerFirstAction == playerId),
                (isSecondActionParalyse && mPlayerSecondAction == playerId),
                (!isSecondActionParalyse && mPlayerSecondAction == playerId)
        ));
    }

    @Override
    public void setResultActionSelection(boolean isMainActionUsed, boolean isKilled, boolean isParalysed, boolean isInfected) {
        if (isMainActionUsed || isKilled){
            if (mPlayerFirstAction >= 0 && mPlayerFirstAction != mPlayerSecondAction) {
                mPersList.get(mPlayerFirstAction).setBackground(Background.NORMAL);
                mPersList.get(mPlayerFirstAction).setSymbolToShow(Symbol.ROUND);
            }
            mPlayerFirstAction = mCurrentTargetPlayer;
            isFirstActionMutate = isMainActionUsed;
            mPersList.get(mPlayerFirstAction).setBackground(Background.ACCENT);
            mPersList.get(mPlayerFirstAction).setSymbolToShow(isMainActionUsed ? Symbol.INSPECT : Symbol.DEAD); // TODO find symbols
        } else {
            if (mPlayerFirstAction == mCurrentTargetPlayer)
                mPlayerFirstAction = -1;
        }
        if (isParalysed || isInfected){
            if (mPlayerSecondAction >= 0 && mPlayerSecondAction != mPlayerFirstAction) {
                mPersList.get(mPlayerSecondAction).setBackground(Background.NORMAL);
                mPersList.get(mPlayerSecondAction).setSymbolToShow(Symbol.ROUND);
            }
            mPlayerSecondAction = mCurrentTargetPlayer;
            isSecondActionParalyse = isParalysed;
            mPersList.get(mPlayerSecondAction).setBackground(Background.ACCENT);
            mPersList.get(mPlayerSecondAction).setSymbolToShow(isParalysed ? Symbol.INSPECT : Symbol.DEAD); // TODO find symbols
        } else {
            if (mPlayerSecondAction == mCurrentTargetPlayer)
                mPlayerSecondAction = -1;
        }
        if (!isMainActionUsed && !isKilled && !isParalysed && !isInfected) {
            mPersList.get(mCurrentTargetPlayer).setBackground(Background.NORMAL);
            mPersList.get(mCurrentTargetPlayer).setSymbolToShow(Symbol.ROUND);
        }
    }

    @Override
    public void returnAction() {
        mIsFinished.call();
    }

    @Override
    public void continueAction() {

    }

    @Override
    public void GMAction() {
        mDispAllPersActivity.call();
    }

    @Override
    public boolean showReturnButton() {
        return true;
    }

    @Override
    public boolean showContinueButton() {
        return true;
    }

    @Override
    public boolean showGMButton() {
        return true;
    }
}
