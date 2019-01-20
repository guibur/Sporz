package com.ham.sporz.viewmodel;

import android.util.Log;

import com.ham.sporz.conductor.ActivityType;
import com.ham.sporz.model.enums.ActionType;
import com.ham.sporz.viewmodel.enums.Background;
import com.ham.sporz.viewmodel.enums.Symbol;

public class SimpleSelectionViewModel extends AbstractSelectionPlayerViewModel {
    private static String TAG = "SimpleSelectionViewModel";

    private int mSelectedPlayer = -1;

    public SimpleSelectionViewModel(){}

    private Symbol getSelectionSymbol(){
        switch (mCurrentGame.getCurrentTurn().getType()){
            case PSYCHOLOGIST:
            case SPY:
            case DETECTIVE:
                return Symbol.INSPECT;
            case GENETICIST:
                return Symbol.MICROSCOPE;
            case CHIEF_ELECTION:
            case CHIEF_ELECTION_2:
                return Symbol.CHIEF;
            default:
                return Symbol.NONE;
        }
    }

    @Override
    public void selectPlayer(int playerId){
        if (mSelectedPlayer > -1) {
            mPersList.get(mSelectedPlayer).setBackground(Background.NORMAL);
            mPersList.get(mSelectedPlayer).setSymbolToShow(Symbol.ROUND);
        }

        if (playerId == mSelectedPlayer){
            mSelectedPlayer = -1;
        } else {
            mSelectedPlayer = playerId;
            mPersList.get(mSelectedPlayer).setBackground(Background.ACCENT);
            mPersList.get(mSelectedPlayer).setSymbolToShow(getSelectionSymbol());
        }
    }

    @Override
    public void returnAction(){
        mIsFinished.call();
    }

    @Override
    public void continueAction() {
        Log.e(TAG, "start show result.");
        if (mSelectedPlayer > -1) {
            cloneGame();
            mNextGame.getCurrentTurn().setNewAction(ActionType.MAIN, mSelectedPlayer);
            switch (mCurrentGame.getCurrentTurn().getType()){
                case PSYCHOLOGIST:
                case GENETICIST:
                    mNextActivity.setValue(ActivityType.SHOW_RESULT);
                    break;
                case SPY:
                case DETECTIVE:
                    mNextActivity.setValue(ActivityType.BUBBLE);
                    break;
            }
        } else {
            cloneGame();
            switchToNextTurn();
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
        // TODO: get prévious rôle
        String text =  "";
        switch (mCurrentGame.getCurrentTurn().getType()){
            case PSYCHOLOGIST:
                text += "Le rôle précédent se rendort… Le PSYCHOLOGUE se réveille…\nIl m'indique de quel joueur il souhaite connaître le statut.";
                break;
            case GENETICIST:
                text += "Le rôle précédent se rendort….Le GÉNÉTICIEN se réveille…\nIl m'indique de quel joueur il souhait connaître le génôme.";
                break;
            case SPY:
                text += "Le rôle précédent se rendort… L'ESPION se réveille…\nIl m'indique de quel joueur il souhaite connaître l'historique cette nuit.";
                break;
            case DETECTIVE:
                text += "Le rôle précédent se rendort… Le DÉTECTIVE se réveille…\nIl m'indique de quel joueur il souhaite connaître l'historique de la nuit précédente.";
                break;
            case CHIEF_ELECTION:
            case CHIEF_ELECTION_2:
                if (mCurrentGame.getCurrentTurn().getPeriodNumber() == 0)
                    text += "Avant de commencer, il vous faut élire un chef.";
                else
                    text += "Le précédent chef est mort. Il est temps de procéder à une nouvelle élection.";
        }
        return text;
    }
}
