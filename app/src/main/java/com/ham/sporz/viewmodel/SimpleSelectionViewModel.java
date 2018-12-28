package com.ham.sporz.viewmodel;

import android.content.Intent;

import com.ham.sporz.model.Player;
import com.ham.sporz.model.enums.ActionType;
import com.ham.sporz.viewmodel.adapter.SimplePersViewModel;

import java.util.ArrayList;

public class SimpleSelectionViewModel extends AbstractMainViewModel implements TwoButtonListener, SelectionPlayerViewModel {

    private int mSelectedPlayer = -1;
    private ArrayList<SimplePersViewModel> mPersList = new ArrayList<>();

    public SimpleSelectionViewModel(){}

    public void addIntent(Intent intentIn) {
        if (mCurrentGame == null) {
            super.addIntent(intentIn);
            for (Player p : mCurrentGame.getPlayers()) {
                mPersList.add(new SimplePersViewModel(p, this));
            }
        }
    }

    @Override
    public ArrayList<SimplePersViewModel> getPersList() {
        return mPersList;
    }

    @Override
    public ActionType getCurrentActionType() {
        return mCurrentGame.getCurrentTurn().getCurrentAction().getType();
    }

    @Override
    public void returnAction(){
        mIsFinished.call();
    }

    @Override
    public void continueAction() { }

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
    public boolean isPlayerSelected(int playerId) {
        return playerId == mSelectedPlayer;
    }

    @Override
    public void selectPlayer(int playerId){
        int formerSelectedPlayer = mSelectedPlayer;
        if (playerId == mSelectedPlayer){
            mSelectedPlayer = -1;
        } else {
            mSelectedPlayer = playerId;
        }
        if (formerSelectedPlayer >= 0)
            mPersList.get(formerSelectedPlayer).updateAfterSelectionChange();
        if (mSelectedPlayer >= 0)
            mPersList.get(mSelectedPlayer).updateAfterSelectionChange();
    }
}
