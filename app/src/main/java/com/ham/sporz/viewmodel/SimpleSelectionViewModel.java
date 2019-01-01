package com.ham.sporz.viewmodel;

import com.ham.sporz.viewmodel.enums.Background;
import com.ham.sporz.viewmodel.enums.Symbol;

public class SimpleSelectionViewModel extends AbstractSelectionPlayerViewModel {

    private int mSelectedPlayer = -1;

    public SimpleSelectionViewModel(){}

    @Override
    public void selectPlayer(int playerId){
        if (mSelectedPlayer > -1) {
            mPersList.get(playerId).setBackground(Background.NORMAL);
            mPersList.get(playerId).setSymbolToShow(Symbol.ROUND);
        }

        if (playerId == mSelectedPlayer){
            mSelectedPlayer = -1;
        } else {
            mSelectedPlayer = playerId;
            mPersList.get(mSelectedPlayer).setBackground(Background.ACCENT);
            mPersList.get(mSelectedPlayer).setSymbolToShow(Symbol.INSPECT);
        }
    }

    @Override
    public void returnAction(){
        mIsFinished.call();
    }

    @Override
    public void continueAction() { }

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
}
