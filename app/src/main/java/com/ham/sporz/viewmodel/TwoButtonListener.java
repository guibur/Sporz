package com.ham.sporz.viewmodel;

import android.view.View;

public interface TwoButtonListener {
    void returnAction();
    void continueAction();
    boolean GMAction();
    boolean showReturnButton();
    boolean showContinueButton();
    boolean showGMButton();
}
