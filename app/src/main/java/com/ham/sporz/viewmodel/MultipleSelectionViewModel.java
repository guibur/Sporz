package com.ham.sporz.viewmodel;

import android.content.Intent;
import android.util.Log;

import com.ham.sporz.model.Player;
import com.ham.sporz.model.enums.ActionType;
import com.ham.sporz.utils.SingleLiveEvent;
import com.ham.sporz.viewmodel.adapter.SimplePersViewModel;

import java.util.ArrayList;

public class MultipleSelectionViewModel extends AbstractMainViewModel implements TwoButtonListener, SelectionPlayerViewModel {

    private final static String TAG = "MultipleSelectionVM";

    private ArrayList<SelectionType> mSelectedPlayers = new ArrayList<>();
    private ArrayList<SimplePersViewModel> mPersList = new ArrayList<>();
    private ActionType mCurrentAction;

    private SingleLiveEvent mShowActionSelectionDialog = new SingleLiveEvent();

    public MultipleSelectionViewModel(){}

    @Override
    public void addIntent(Intent intentIn) {
        Log.d(TAG, "Adding intent");
        if (mCurrentGame == null) {
            Log.d(TAG, "New intent.");
            super.addIntent(intentIn);
            Log.d(TAG, "Intent added");
            for (Player p : mCurrentGame.getPlayers()) {
                Log.d(TAG, "iterating on the player " + String.valueOf(p.getId()));
                mSelectedPlayers.add(SelectionType.NONE);
                mPersList.add(new SimplePersViewModel(p, this));
            }
        }
        Log.d(TAG, "sel play size = " + String.valueOf(mSelectedPlayers.size()));
        Log.d(TAG, "pers list size = " + String.valueOf(mPersList.size()));
        mCurrentAction = mCurrentGame.getCurrentTurn().getCurrentAction().getType();
    }

    public SingleLiveEvent getShowActionSelectionDialog() {
        return mShowActionSelectionDialog;
    }

    public ActionSelectionViewModel getActionSelectionVM(int playerId){
        return new ActionSelectionViewModel(this, playerId);
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
    public boolean GMAction() {
        mDispAllPersActivity.call();
        return true;
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
    public SelectionType getPlayerSelection(int playerId) {
        Log.d(TAG, "get player selection.");
        Log.d(TAG, String.valueOf("sel play size = " + mSelectedPlayers.size()));
        return mSelectedPlayers.get(playerId);
    }

    @Override
    public void selectPlayer(int playerId){
        switch (mCurrentAction){
            case GENETICIST:
            case SPY:
            case PSYCHOLOGIST:
            case DETECTIVE:
                // Single selection of the main action
                int formerSelectedPlayer = mSelectedPlayers.indexOf(SelectionType.MAIN_ABILITY);
                if (playerId == formerSelectedPlayer){
                    mSelectedPlayers.set(playerId, SelectionType.NONE);
                } else {
                    mSelectedPlayers.set(playerId, SelectionType.MAIN_ABILITY);
                    if (formerSelectedPlayer > -1)
                        mSelectedPlayers.set(formerSelectedPlayer, SelectionType.NONE);
                }
                Log.d(TAG, String.valueOf(mPersList.size()));
                if (formerSelectedPlayer >= 0)
                    mPersList.get(formerSelectedPlayer).updateAfterSelectionChange();
                if (playerId >= 0)
                    mPersList.get(playerId).updateAfterSelectionChange();
                break;
        }
    }
}
