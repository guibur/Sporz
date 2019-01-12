package com.ham.sporz.viewmodel;

import com.ham.sporz.viewmodel.enums.Background;
import com.ham.sporz.viewmodel.enums.Symbol;

public class SimpleSelectionViewModel extends AbstractSelectionPlayerViewModel {

    private int mSelectedPlayer = -1;

    public SimpleSelectionViewModel(){}

    @Override
    public void selectPlayer(int playerId){
        if (mSelectedPlayer > -1) {
            mPersList.get(mSelectedPlayer).setBackground(Background.NORMAL);
            mPersList.get(mSelectedPlayer).setSymbolToShow(Symbol.ROUND);
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

    @Override
    public boolean showSpeechBubble() {
        return true;
    }

    @Override
    public String getSpeechBubbleText() {
        // TODO: get prévious rôle
        String text =  "Le rôle précédent se rendort…";
        switch (mCurrentGame.getCurrentTurn().getCurrentAction().getType()){
            case PSYCHOLOGIST:
                text += "Le PSYCHOLOGUE se réveille…\nIl m'indique de quel joueur il souhaite connaître le statut.";
                break;
            case GENETICIST:
                text += "Le GÉNÉTICIEN se réveille…\nIl m'indique de quel joueur il souhait connaître le génôme.";
                break;
            case SPY:
                text += "L'ESPION se réveille…\nIl m'indique de quel joueur il souhaite connaître l'historique cette nuit.";
                break;
            case DETECTIVE:
                text += "Le DÉTECTIVE se réveille…\nIl m'indique de quel joueur il souhaite connaître l'historique de la nuit précédente.";
                break;
        }
        return text;
    }
}
