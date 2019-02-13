package com.ham.sporz.viewmodel;

import com.ham.sporz.conductor.ActivityType;
import com.ham.sporz.model.Action;
import com.ham.sporz.model.Turn;
import com.ham.sporz.model.enums.ActionType;
import com.ham.sporz.model.enums.Role;

import java.util.LinkedList;

public class ComputerScientistViewModel extends AbstractMainViewModel implements TwoButtonListener {
    static public final String TAG="ComputerScientistViewModel";

    @Override
    public void returnAction() {
        mIsFinished.call();
    }

    @Override
    public void continueAction() {
        // TODO add action
        mNextGame = mCurrentGame;
        mNextActivity.setValue(ActivityType.SHOW_RESULT);
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
       return "Le rôle précédent se rendort…\nL'INFORMATICIEN se réveille…\nJe lui indique le nombre de mutants à bord.";
    }

    public String getComputerScientist(){
        return mCurrentGame.getPlayers(Role.COMPUTER_SCIENTIST).get(0).getName();
    }

    public boolean isParalysed() {
        return mCurrentGame.getPlayers(Role.COMPUTER_SCIENTIST).get(0).isParalyzed();
    }
}
