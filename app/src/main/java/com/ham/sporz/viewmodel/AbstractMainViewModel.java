package com.ham.sporz.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.util.Log;

import com.ham.sporz.conductor.ActivityType;
import com.ham.sporz.model.Game;
import com.ham.sporz.model.Player;
import com.ham.sporz.model.Turn;
import com.ham.sporz.utils.SingleLiveEvent;

import java.util.ArrayList;

public abstract class AbstractMainViewModel extends ViewModel {
    private static final String TAG = "AbstractViewModel";

    protected Game mCurrentGame;
    protected SingleLiveEvent mIsFinished = new SingleLiveEvent();
    protected SingleLiveEvent mDispAllPersActivity = new SingleLiveEvent();
    protected SingleLiveEvent<ActivityType> mNextActivity = new SingleLiveEvent<ActivityType>();
    protected Game mNextGame;

    public void addIntent(Intent intentIn) {
        mCurrentGame = intentIn.getParcelableExtra("currentGame");
    }

    public MutableLiveData getIsFinished() {
        return mIsFinished;
    }

    public MutableLiveData dispAllPersActivity(){
        return mDispAllPersActivity;
    }

    public Game getCurrentGame(){
        return mCurrentGame;
    }

    public Game getNextGame(){
        return mNextGame;
    }

    public ArrayList<Player> getPlayerList(){
        return mCurrentGame.getPlayers();
    }

    public MutableLiveData<ActivityType> getNextActivity(){
        return mNextActivity;
    }

    protected ActivityType getActivityFromTurn(Turn turn){
        switch(turn.getType()){
            case MUTANT:
                return ActivityType.MUTANT_SELECTION;
            case DOCTOR:
                return ActivityType.DOCTOR_SELECTION;
            case PSYCHOLOGIST:
            case GENETICIST:
            case SPY:
            case DETECTIVE:
            case CHIEF_ELECTION:
            case CHIEF_ELECTION_2:
                return ActivityType.SIMPLE_SELECTION;
            case COMPUTER_SCIENTIST:
                return ActivityType.BUBBLE;
            case POPULAR_VOTING:
                return ActivityType.VOTE;
            case HACKER:
            default:
                return null;
        }
    }

    protected void cloneGame(){
        try{
            mNextGame = mCurrentGame.clone();
        } catch(CloneNotSupportedException e) {
            e.printStackTrace(System.err);
        }
    }

    protected void switchToNextTurn(){
        Log.e(TAG, "start next activity.");

        mNextGame.setNewTurn();
        Log.e(TAG, "Setting turn " + mNextGame.getCurrentTurn().getType().name());
        mNextActivity.setValue(getActivityFromTurn(mNextGame.getCurrentTurn()));
    }
}
