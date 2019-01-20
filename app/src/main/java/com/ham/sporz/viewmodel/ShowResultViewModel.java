package com.ham.sporz.viewmodel;

import android.util.Log;

import com.ham.sporz.R;
import com.ham.sporz.conductor.ActivityType;
import com.ham.sporz.conductor.Conductor;
import com.ham.sporz.model.enums.TurnType;
import com.ham.sporz.model.enums.Genome;
import com.ham.sporz.model.enums.Role;

public class ShowResultViewModel extends AbstractMainViewModel implements TwoButtonListener {
    private static final String TAG = "ShowResultViewModel";

    public String getName(){
        if (mCurrentGame.getCurrentTurn().getType() == TurnType.COMPUTER_SCIENTIST){
            return "";
        }
        int currentPlayer = mCurrentGame.getCurrentTurn().getAction(0).getTarget();
        return mCurrentGame.getPlayer(currentPlayer).getName();
    }

    public Role getCurrentRole(){
        return mCurrentGame.getCurrentTurn().getType().getAssociatedRole();
    }

    public boolean isMutation(){
        return mCurrentGame.getCurrentTurn().getType() == TurnType.PSYCHOLOGIST;
    }

    public Role getMutationSymbol(){
        if (mCurrentGame.getCurrentTurn().getActionNumber() > 0) {
            int currentPlayer = mCurrentGame.getCurrentTurn().getAction(0).getTarget();
            boolean isCurrentPlayerMutant = mCurrentGame.getPlayer(currentPlayer).isMutant();
            Log.e(TAG, "Show mutation symbol : " + String.valueOf(isCurrentPlayerMutant));
            return isCurrentPlayerMutant ? Role.MUTANT_BASE : Role.ASTRONAUT;
        }
        else
            return Role.DOCTOR;
    }

    public boolean isGenome(){
        return mCurrentGame.getCurrentTurn().getType() == TurnType.GENETICIST;
    }

    public Genome getGenSymbol(){
        if (mCurrentGame.getCurrentTurn().getActionNumber() > 0) {
            int currentPlayer = mCurrentGame.getCurrentTurn().getAction(0).getTarget();
            return mCurrentGame.getPlayer(currentPlayer).getGenome();
        }
        else
            return Genome.NORMAL;
    }

    public boolean isMutantNumber(){
        return mCurrentGame.getCurrentTurn().getType() == TurnType.COMPUTER_SCIENTIST;
    }

    public String getMutantNumber(){
        return String.valueOf(mCurrentGame.getMutants().size());
    }

    public int getCaption(){
        switch (mCurrentGame.getCurrentTurn().getType()){
            case PSYCHOLOGIST:
                switch (getMutationSymbol()){
                    case ASTRONAUT:
                        return R.string.Sound;
                    case MUTANT_BASE:
                        return R.string.Mutated;
                }
            case GENETICIST:
                switch (getGenSymbol()){
                    case NORMAL:
                        return R.string.normal;
                    case RESISTANT:
                        return R.string.resistant;
                    case HOST:
                        return R.string.host;
                }
            case COMPUTER_SCIENTIST:
                if (Integer.valueOf(getMutantNumber()) > 1){
                    return R.string.mutated_plural;
                } else {
                    return R.string.mutated_sing;
                }
            default:
                return 0;
        }
    }

    public int getBackground(){
        switch (mCurrentGame.getCurrentTurn().getType()){
            case PSYCHOLOGIST:
                switch (getMutationSymbol()){
                    case ASTRONAUT:
                        return R.color.colorPrimary_2;
                    case MUTANT_BASE:
                        return R.color.colorAccent_2;
                }
            case GENETICIST:
                switch (getGenSymbol()){
                    case NORMAL:
                        return R.color.colorPrimary_2;
                    case RESISTANT:
                        return R.color.colorAccent_2;
                    case HOST:
                        return R.color.colorPrimaryDark_1;
                }
            case COMPUTER_SCIENTIST:
            default:
                return R.color.colorPrimary_2;
        }
    }

    @Override
    public void returnAction() {
        mIsFinished.call();
    }

    @Override
    public void continueAction() {
        cloneGame();
        switchToNextTurn();
    }

    @Override
    public void GMAction() {

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
        return false;
    }

    @Override
    public boolean showSpeechBubble() {
        return false;
    }

    @Override
    public String getSpeechBubbleText() {
        return "";
    }
}
