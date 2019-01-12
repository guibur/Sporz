package com.ham.sporz.viewmodel;

import com.ham.sporz.R;
import com.ham.sporz.model.enums.ActionType;
import com.ham.sporz.model.enums.Genome;
import com.ham.sporz.model.enums.Role;

public class ShowResultViewModel extends AbstractMainViewModel implements TwoButtonListener {

    public boolean isMutation(){
        return mCurrentGame.getCurrentTurn().getCurrentAction().getType() == ActionType.PSYCHOLOGIST;
    }

    public Role getRoleSymbol(){
        int currentPlayer = mCurrentGame.getCurrentTurn().getCurrentAction().getTarget();
        boolean isCurrentPlayerMutant = mCurrentGame.getPlayer(currentPlayer).isMutant();
        return isCurrentPlayerMutant ? Role.MUTANT_BASE : Role.ASTRONAUT;
    }

    public boolean isGenome(){
        return mCurrentGame.getCurrentTurn().getCurrentAction().getType() == ActionType.GENETICIST;
    }

    public Genome getGenSymbol(){
        int currentPlayer = mCurrentGame.getCurrentTurn().getCurrentAction().getTarget();
        return mCurrentGame.getPlayer(currentPlayer).getGenome();
    }

    public boolean isMutantNumber(){
        return mCurrentGame.getCurrentTurn().getCurrentAction().getType() == ActionType.COMPUTER_SCIENTIST;
    }

    public String getNumberMutant(){
        return String.valueOf(mCurrentGame.getMutants().size());
    }

    public int getCaption(){
        return R.string.resistant;
    }

    public int getBackground(){
//        return R.color.colorPrimary_2;
//        return R.color.colorAccent_2;
        return R.color.colorPrimaryDark_1;
    }

    @Override
    public void returnAction() {
        mIsFinished.call();
    }

    @Override
    public void continueAction() {

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
