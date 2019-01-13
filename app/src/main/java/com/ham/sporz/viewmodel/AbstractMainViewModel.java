package com.ham.sporz.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Intent;

import com.ham.sporz.conductor.ActivityType;
import com.ham.sporz.model.Game;
import com.ham.sporz.model.Player;
import com.ham.sporz.utils.SingleLiveEvent;

import java.util.ArrayList;

public abstract class AbstractMainViewModel extends ViewModel {
    protected Game mCurrentGame;
    protected SingleLiveEvent mIsFinished = new SingleLiveEvent();
    protected SingleLiveEvent mDispAllPersActivity = new SingleLiveEvent();
    protected SingleLiveEvent<ActivityType> mNextActivity = new SingleLiveEvent<ActivityType>();

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

    public ArrayList<Player> getPlayerList(){
        return mCurrentGame.getPlayers();
    }
}
