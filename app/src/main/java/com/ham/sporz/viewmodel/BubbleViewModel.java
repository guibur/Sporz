package com.ham.sporz.viewmodel;

import com.ham.sporz.R;
import com.ham.sporz.conductor.ActivityType;
import com.ham.sporz.model.enums.TurnType;
import com.ham.sporz.viewmodel.AbstractMainViewModel;
import com.ham.sporz.viewmodel.TwoButtonListener;

public class BubbleViewModel extends AbstractMainViewModel implements TwoButtonListener {
    @Override
    public void returnAction() {
        mIsFinished.call();
    }

    @Override
    public void continueAction() {
        switch (mCurrentGame.getCurrentTurn().getType()) {
            case COMPUTER_SCIENTIST:
                mNextGame = mCurrentGame;
                mNextActivity.setValue(ActivityType.SHOW_RESULT);
                break;
        }
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
        switch (mCurrentGame.getCurrentTurn().getType()){
            case COMPUTER_SCIENTIST:
//                return "Le rôle précédent se rendort…\nL'INFORMATICIEN se réveille…\nJe lui indique le nombre de mutants à bord.";
            case SPY:
                String raw_text = "Je lui indique si<br/> <b>AAAAA</b> :  <img src=\"thumb_%s\"";
                return String.format(raw_text, "up");
        }
        return "";
    }
}
