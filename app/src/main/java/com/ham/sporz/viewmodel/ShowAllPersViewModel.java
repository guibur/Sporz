package com.ham.sporz.viewmodel;

import com.ham.sporz.model.Player;

import java.util.ArrayList;

public class ShowAllPersViewModel extends AbstractMainViewModel implements TwoButtonListener {

    public ShowAllPersViewModel() {    }

    public ArrayList<Player> getPlayerList(){
        return mCurrentGame.getPlayers();
    }

    @Override
    public void returnAction(){
        mIsFinished.call();
    }

    @Override
    public void continueAction() { }

    @Override
    public boolean GMAction() { return true; }

    @Override
    public boolean showReturnButton() {
        return true;
    }

    @Override
    public boolean showContinueButton() {
        return false;
    }

    @Override
    public boolean showGMButton() {
        return false;
    }
}
