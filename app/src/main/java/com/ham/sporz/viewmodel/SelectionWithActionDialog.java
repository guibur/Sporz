package com.ham.sporz.viewmodel;

import android.arch.lifecycle.MutableLiveData;

public interface SelectionWithActionDialog {
    MutableLiveData<ActionSelectionDialogViewModel> getActionSelectionDialogVM();
    void setResultActionSelection(boolean isMainActionUsed, boolean isKilled,
                                  boolean isParalysed, boolean isInfected);
}
