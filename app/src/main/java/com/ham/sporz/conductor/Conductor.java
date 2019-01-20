package com.ham.sporz.conductor;

import com.ham.sporz.model.Game;
import com.ham.sporz.model.enums.Role;
import com.ham.sporz.model.enums.TurnType;

import java.util.ArrayList;

public class Conductor {
    private static ArrayList<TurnType> nigtTurnOrder = new ArrayList<TurnType>(){{
        add(TurnType.MUTANT);
        add(TurnType.DOCTOR);
        add(TurnType.PSYCHOLOGIST);
        add(TurnType.GENETICIST);
        add(TurnType.COMPUTER_SCIENTIST);
        add(TurnType.SPY);
        add(TurnType.DETECTIVE);
        add(TurnType.HACKER);
}};
    public static TurnType getNextTurn(Game currentGame){
        int currentTurnIdx = Conductor.nigtTurnOrder.indexOf(currentGame.getCurrentTurn());
        for (int t = currentTurnIdx + 1; t < Conductor.nigtTurnOrder.size(); ++t){
            switch (Conductor.nigtTurnOrder.get(t)){
                case DOCTOR:
                    if (currentGame.getAlivePlayers(Role.DOCTOR).size() > 0){
                        return TurnType.DOCTOR;
                    }
            }
        }
        return null;
    }
}
