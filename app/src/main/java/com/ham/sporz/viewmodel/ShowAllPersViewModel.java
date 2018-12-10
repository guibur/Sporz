package com.ham.sporz.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Intent;

import com.ham.sporz.model.Game;
import com.ham.sporz.model.Player;

import java.util.ArrayList;

public class ShowAllPersViewModel extends ViewModel implements ReturnButtonListener {
    private Game mCurrentGame;
    private MutableLiveData<Boolean> mIsFinished;

    public ShowAllPersViewModel(Intent intentIn) {
        mCurrentGame = intentIn.getParcelableExtra("currentGame");
        mIsFinished = new MutableLiveData<Boolean>();
        mIsFinished.setValue(Boolean.FALSE);
    }

    public ArrayList<Player> getPlayerList(){
        return mCurrentGame.getPlayers();
    }

    public MutableLiveData<Boolean> getIsFinished() {
        return mIsFinished;
    }

    public void returnAction(){
        mIsFinished.setValue(Boolean.TRUE);
    }
}
