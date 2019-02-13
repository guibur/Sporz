package com.ham.sporz.viewmodel;

import android.util.Log;

import com.ham.sporz.R;
import com.ham.sporz.conductor.ActivityType;
import com.ham.sporz.model.Action;
import com.ham.sporz.model.Turn;
import com.ham.sporz.model.enums.ActionType;
import com.ham.sporz.model.enums.TurnType;
import com.ham.sporz.viewmodel.AbstractMainViewModel;
import com.ham.sporz.viewmodel.TwoButtonListener;

import java.util.LinkedList;

public class BubbleViewModel extends AbstractMainViewModel implements TwoButtonListener {
    static public final String TAG="BubbleViewModel";

    @Override
    public void returnAction() {
        mIsFinished.call();
    }

    @Override
    public void continueAction() {
        switch (mCurrentGame.getCurrentTurn().getType()) {
            case COMPUTER_SCIENTIST:
                mNextGame = mCurrentGame;
                mNextActivity.setValue(ActivityType.SHOW_RESULT);
                break;
        }
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

    private String getSpeechForNightInspection(LinkedList<Turn> nightTurns, int target){
        String text = "Je lui indique si le joueur a été :<br/>";

        // Keep infection outside of for loop as it will be needed for all the roles.
        boolean infected = false;

        for (Turn turn: nightTurns){
            switch (turn.getType()){
                case MUTANT:
                    // should be inspected first
                    boolean mutated = false;
                    boolean paralysed = false;;
                    for (Action action: turn.getActionList()){
                        if (action.getTarget() == target){
                            switch (action.getType()){
                                case MAIN:
                                    mutated = true;
                                    break;
                                case INFECT:
                                    infected = true;
                                    break;
                                case PARALYSE:
                                    paralysed = true;
                                    break;
                            }
                        }
                    }
                    text += String.format("    - <b>infecté</b> (ou non) : <img src=\"%s\"><br/>",
                            infected ? "thumb_up": "thumb_down");
                    text += String.format("    - <b>muté</b> (ou non) : <img src=\"%s\"><br/>",
                            infected ? "infected": (mutated ? "thumb_up": "thumb_down"));
                    text += String.format("    - <b>paralysé</b> (ou non) : <img src=\"%s\"><br/>",
                            infected ? "infected": (paralysed ? "thumb_up": "thumb_down"));
                    break;

                case DOCTOR:
                    // Should be inspected second.
                    boolean healed = false;
                    for (Action action: turn.getActionList()){
                        if (action.getTarget() == target && action.getType() == ActionType.MAIN){
                            healed = true;
                        }
                    }
                    text += String.format("    - <b>soigné</b> (ou non) : <img src=\"%s\"><br/>",
                            infected ? "infected": (healed ? "thumb_up": "thumb_down"));
                    break;

                case PSYCHOLOGIST:
                    boolean inspected_psy = false;
                    if (turn.getActionNumber() > 0)
                        inspected_psy = turn.getAction(0).getTarget() == target;
                    text += String.format("    - inspecté par le <b>psychologue</b> (ou non) : <img src=\"%s\"><br/>",
                            infected ? "infected": (inspected_psy ? "thumb_up": "thumb_down"));
                    break;
                case GENETICIST:
                    boolean inspected_gen = false;
                    if (turn.getActionNumber() > 0)
                        inspected_gen = turn.getAction(0).getTarget() == target;
                    text += String.format("    - inspecté par le <b>généticien</b> (ou non) : <img src=\"%s\">",
                            infected ? "infected": ( inspected_gen ? "thumb_up": "thumb_down"));
                    break;
            }
        }
        return text;
    }

    @Override
    public String getSpeechBubbleText() {
        switch (mCurrentGame.getCurrentTurn().getType()){
            case COMPUTER_SCIENTIST:
               return "Le rôle précédent se rendort…\nL'INFORMATICIEN se réveille…\nJe lui indique le nombre de mutants à bord.";
            case SPY:
                int target_spy = mCurrentGame.getCurrentTurn().getAction(0).getTarget();
                LinkedList<Turn> nightTurns_spy = mCurrentGame.getCurrentNightTurns();
                return this.getSpeechForNightInspection(nightTurns_spy, target_spy);
            case DETECTIVE:
                int target_detective = mCurrentGame.getCurrentTurn().getAction(0).getTarget();
                LinkedList<Turn> nightTurns_detective = mCurrentGame.getLastNightTurns();
                return this.getSpeechForNightInspection(nightTurns_detective, target_detective);
            case MUTANT:
                String mutant_text = "Je vais passer parmi vous pour vous indiquer les modifications de ce tour. Je vais taper:\n";
                mutant_text += "     - Sur la tête si vous avez été muté (deux fois si cette mutation a échoué)";
                mutant_text += "     - Sur le cou si vous avez été tué";
                mutant_text += "     - Sur l'épaule si vous avez été paralysé";
                mutant_text += "     - Sur le front si vous avez été infecté";
                return mutant_text;
            case DOCTOR:
                String doctor_text = "Je vais passer parmi vous pour vous indiquer les modifications de ce tour. Je vais taper:\n";
                doctor_text += "     - Sur la tête si vous avez été soigné (deux fois si ce soin a échoué)";
                doctor_text += "     - Sur le cou si vous avez été euthanasié";
                return doctor_text;
        }
        return "";
    }
}
