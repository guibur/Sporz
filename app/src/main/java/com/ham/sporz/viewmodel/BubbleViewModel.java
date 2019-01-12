package com.ham.sporz.viewmodel;

import com.ham.sporz.viewmodel.AbstractMainViewModel;
import com.ham.sporz.viewmodel.TwoButtonListener;

public class BubbleViewModel extends AbstractMainViewModel implements TwoButtonListener {
    @Override
    public void returnAction() {
        mIsFinished.call();
    }

    @Override
    public void continueAction() {

    }

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

    @Override
    public boolean showSpeechBubble() {
        return true;
    }

    @Override
    public String getSpeechBubbleText() {
        return "blablabla";
    }
}
