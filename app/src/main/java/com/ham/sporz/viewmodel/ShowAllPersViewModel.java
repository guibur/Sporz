package com.ham.sporz.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import android.view.View;

import com.ham.sporz.model.Game;
import com.ham.sporz.model.Player;

import java.util.ArrayList;

public class ShowAllPersViewModel extends ViewModel implements TwoButtonListener {
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

    @Override
    public void returnAction(){
        mIsFinished.setValue(Boolean.TRUE);
    }

    @Override
    public void continueAction() { }

    @Override
    public int showReturnButton() {
        return View.VISIBLE;
    }

    @Override
    public int showContinueButton() {
        return View.GONE;
    }
}
