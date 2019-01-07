package com.ham.sporz.viewmodel;

import com.ham.sporz.R;
import com.ham.sporz.model.enums.Genome;
import com.ham.sporz.model.enums.Role;

public class ShowResultViewModel extends AbstractMainViewModel implements TwoButtonListener {

    public boolean isMutation(){
        return false;
    }

    public Role getRoleSymbol(){
        return Role.ASTRONAUT;
    }

    public boolean isGenome(){
        return false;
    }

    public Genome getGenSymbol(){
        return Genome.RESISTANT;
    }

    public boolean isMutantNumber(){
        return true;
    }

    public String getNumberMutant(){
        return String.valueOf(mCurrentGame.getMutants().size());
    }

    public int getCaption(){
        return R.string.resistant;
    }

    public String getBackground(){
        return "#00FF00";
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
}
