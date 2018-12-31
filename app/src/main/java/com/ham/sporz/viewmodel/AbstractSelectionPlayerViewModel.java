package com.ham.sporz.viewmodel;

import com.ham.sporz.utils.SingleLiveEvent;
import com.ham.sporz.viewmodel.adapter.SimplePersViewModel;

import java.util.ArrayList;

public abstract class AbstractSelectionPlayerViewModel extends AbstractMainViewModel {
    protected SingleLiveEvent<ActionSelectionDialog>

    public abstract ArrayList<SimplePersViewModel> getPersList();
    public abstract void selectPlayer(int playerId);
}
