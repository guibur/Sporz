package com.ham.sporz.viewmodel;

import android.content.Intent;

import com.ham.sporz.model.Player;
import com.ham.sporz.viewmodel.adapter.SimplePersViewModel;
import com.ham.sporz.viewmodel.enums.Background;
import com.ham.sporz.viewmodel.enums.Symbol;

import java.util.ArrayList;

public abstract class AbstractSelectionPlayerViewModel extends AbstractMainViewModel implements TwoButtonListener {

    protected ArrayList<SimplePersViewModel> mPersList = new ArrayList<>();

    // Interface Activity -> VM
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

    // Interface VM -> Activity
    public ArrayList<SimplePersViewModel> getPersList() {
        return mPersList;
    }
    // Interface Adapter -> VM
    public abstract void selectPlayer(int playerId);
}
