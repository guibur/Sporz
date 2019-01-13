package com.ham.sporz.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.util.Log;

import com.ham.sporz.model.enums.TurnType;
import com.ham.sporz.model.enums.Role;
import com.ham.sporz.utils.SingleLiveEvent;
import com.ham.sporz.viewmodel.enums.Background;
import com.ham.sporz.viewmodel.enums.Symbol;

import java.util.LinkedList;

public class DoctorSelectionPlayerViewModel extends AbstractSelectionPlayerViewModel implements SelectionWithActionDialog {
    private String TAG = "DoctorSelectionPlayerVM";

    private SingleLiveEvent<ActionSelectionDialogViewModel> mActionSelectionDialog = new SingleLiveEvent<>();
    private int mCurrentTargetPlayer = -1;
    private int nDoctors = 0;
    private LinkedList<Integer> mSeletedPlayer = new LinkedList<>();
    private LinkedList<Boolean> mIsSelectionForHealing = new LinkedList<>();

    @Override
    public void addIntent(Intent intentIn){
        super.addIntent(intentIn);
        Log.e(TAG, "Constructor Doctor selection VM");
        nDoctors = mCurrentGame.getAlivePlayers(Role.DOCTOR).size();
    }

    @Override
    public MutableLiveData<ActionSelectionDialogViewModel> getActionSelectionDialogVM() {
        return mActionSelectionDialog;
    }

    @Override
    public void selectPlayer(int playerId) {
        mCurrentTargetPlayer = playerId;
        mActionSelectionDialog.setValue(new ActionSelectionDialogViewModel(
                this,
                TurnType.DOCTOR,
                (!mSeletedPlayer.contains(playerId) && mSeletedPlayer.size() == nDoctors),
                true,
                (mSeletedPlayer.contains(playerId) && mIsSelectionForHealing.get(mSeletedPlayer.indexOf(playerId))),
                (mSeletedPlayer.contains(playerId) && !mIsSelectionForHealing.get(mSeletedPlayer.indexOf(playerId))),
                false,
                false
        ));
    }

    @Override
    public void setResultActionSelection(boolean isMainActionUsed, boolean isKilled, boolean isParalysed, boolean isInfected) {
        if (isMainActionUsed || isKilled){
            // Remove first selected if limit of selection number
            if (mSeletedPlayer.size() == nDoctors) {
                int playerToRemove = mSeletedPlayer.pollFirst();
                mIsSelectionForHealing.pollFirst();
                mPersList.get(playerToRemove).setBackground(Background.NORMAL);
                mPersList.get(playerToRemove).setSymbolToShow(Symbol.ROUND);
            }
            // Change background if selection
            if (mSeletedPlayer.contains(mCurrentTargetPlayer)) {
                int indexFormerSelectionCurrentPlayer = mSeletedPlayer.indexOf(mCurrentTargetPlayer);
                mIsSelectionForHealing.set(indexFormerSelectionCurrentPlayer, isMainActionUsed);
            } else {
                mSeletedPlayer.addLast(mCurrentTargetPlayer);
                mIsSelectionForHealing.addLast(isMainActionUsed);
            }
            mPersList.get(mCurrentTargetPlayer).setBackground(Background.ACCENT);
            mPersList.get(mCurrentTargetPlayer).setSymbolToShow(isMainActionUsed ? Symbol.INSPECT : Symbol.DEAD); // TODO find symbols
        } else {
            // Remove selection if need be
            if (mSeletedPlayer.contains(mCurrentTargetPlayer)){
                int indexFormerSelection = mSeletedPlayer.indexOf(mCurrentTargetPlayer);
                mSeletedPlayer.remove(indexFormerSelection);
                mIsSelectionForHealing.remove(indexFormerSelection);
                // Clear background since no action is affecting the player
                mPersList.get(mCurrentTargetPlayer).setBackground(Background.NORMAL);
                mPersList.get(mCurrentTargetPlayer).setSymbolToShow(Symbol.ROUND);
            }
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

    @Override
    public boolean showSpeechBubble() {
        return true;
    }

    @Override
    public String getSpeechBubbleText() {
        return "Les MUTANTS se rendorment… Les MÉDECINS se réveillent…\nIls m'indiquent qui il souhaitent guérir (ou tuer).";
    }
}
