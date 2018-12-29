package com.ham.sporz.viewmodel.adapter;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.ham.sporz.model.Player;
import com.ham.sporz.model.enums.ActionType;
import com.ham.sporz.viewmodel.SelectionPlayerViewModel;
import com.ham.sporz.viewmodel.enums.Background;
import com.ham.sporz.viewmodel.enums.Symbol;

public class SimplePersViewModel extends ViewModel implements CardViewModel {
    private Player mPlayer;
    private ActionType mActionType;
    private SelectionPlayerViewModel mParentViewModel;
    private MutableLiveData<Symbol> mSymbolToShow = new MutableLiveData<>();
    private MutableLiveData<Background> mBackground = new MutableLiveData<>();

    public SimplePersViewModel(Player player, SelectionPlayerViewModel parentViewModel) {
        mPlayer = player;
        mParentViewModel = parentViewModel;
        mActionType = parentViewModel.getCurrentActionType();
        updateAfterSelectionChange();
    }

    public int getId(){
        return mPlayer.getId();
    }

    public String getName(){
        return mPlayer.getName();
    }

    public boolean isClickable(){
        return mPlayer.isAlive();
    }

    public void updateAfterSelectionChange(){
        updateSymbol();
        updateBackground();
    }

    // TODO remove dependency toward parent VM once Multiple selection is implemented.
    private void updateSymbol(){
        if (mPlayer.isDead())
            mSymbolToShow.setValue(Symbol.DEAD);
        else if (SelectionPlayerViewModel.SelectionType.MAIN_ABILITY
                == mParentViewModel.getPlayerSelection(mPlayer.getId()))
            mSymbolToShow.setValue(Symbol.INSPECT);
        else
            mSymbolToShow.setValue(Symbol.ROUND);
    }

    public MutableLiveData<Symbol> getSymbol(){
        updateSymbol();
        return mSymbolToShow;
    }

    private void updateBackground(){
        if (SelectionPlayerViewModel.SelectionType.MAIN_ABILITY
                == mParentViewModel.getPlayerSelection(mPlayer.getId()))
            mBackground.setValue(Background.ACCENT);
        else if (mActionType.getAssociatedRole() == mPlayer.getRole())
            mBackground.setValue(Background.DARK);
        else
            mBackground.setValue(Background.NORMAL);
    }

    @Override
    public MutableLiveData<Background> getBackground() {
        return mBackground;
    }

    @Override
    public boolean showInactiveFilter() {
        return mPlayer.isDead();
    }
}
