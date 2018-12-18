package com.ham.sporz.model.enums;

import com.ham.sporz.R;

public enum Role {
    MUTANT_BASE(R.string.mutant_base),
    DOCTOR(R.string.doctor),
    PSYCHOLOGIST(R.string.psychologist),
    GENETICIST(R.string.geneticist),
    COMPUTER_SCIENTIST(R.string.computer_scientist),
    SPY(R.string.spy),
    DETECTIVE(R.string.detective),
    HACKER(R.string.hacker),
    TRAITOR(R.string.traitor),
    ASTRONAUT(R.string.astronaut),
    NOT_A_ROLE(0);

    private int mStringId;

    Role(int stringId) {
        mStringId = stringId;
    }

    public int getStringId(){
        return mStringId;
    }
}
