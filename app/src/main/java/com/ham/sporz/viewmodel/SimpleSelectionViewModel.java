package com.ham.sporz.viewmodel;

import android.content.Intent;

import com.ham.sporz.model.Player;
import com.ham.sporz.model.enums.ActionType;
import com.ham.sporz.viewmodel.adapter.SimplePersViewModel;
import com.ham.sporz.viewmodel.enums.Background;
import com.ham.sporz.viewmodel.enums.Symbol;

import java.util.ArrayList;

public class SimpleSelectionViewModel extends AbstractSelectionPlayerViewModel implements TwoButtonListener {

    private int mSelectedPlayer = -1;
    private ArrayList<SimplePersViewModel> mPersList = new ArrayList<>();

    public SimpleSelectionViewModel(){}

    public void addIntent(Intent intentIn) {
        if (mCurrentGame == null) {
            super.addIntent(intentIn);
            for (Player p : mCurrentGame.getPlayers()) {
                SimplePersViewModel simplePersViewModel = new SimplePersViewModel(p);
                mPersList.add(simplePersViewModel);
                if (p.isDead()){
                    simplePersViewModel.setSymbolToShow(Symbol.DEAD);
                    simplePersViewModel.setBackground(p.isMutant() ? Background.DARK : Background.NORMAL);
                    simplePersViewModel.setInactiveFilter(true);
                } else {
                    simplePersViewModel.setSymbolToShow(Symbol.ROUND);
                }
            }
        }
    }

    @Override
    public void selectPlayer(int playerId){
        if (mSelectedPlayer > -1) {
            mPersList.get(playerId).setBackground(mCurrentGame.getPlayer(mSelectedPlayer).isMutant() ? Background.DARK : Background.NORMAL);
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
    public ArrayList<SimplePersViewModel> getPersList() {
        return mPersList;
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
