package com.ham.sporz.viewmodel;

import com.ham.sporz.model.enums.ActionType;
import com.ham.sporz.viewmodel.adapter.SimplePersViewModel;

import java.util.ArrayList;

public interface SelectionPlayerViewModel {

    enum SelectionType {
        NONE,
        MAIN_ABILITY,
        KILL,
        PARALYSE,
        INFECT
    }

    SelectionType getPlayerSelection(int playerId);
    ArrayList<SimplePersViewModel> getPersList();
    ActionType getCurrentActionType();
    void selectPlayer(int playerId);
}
