package com.ham.sporz.model.enums;

public enum TurnType {
    GAME_INSTANTIATION(Role.NOT_A_ROLE),
    CHIEF_ELECTION(Role.NOT_A_ROLE),
    CHIEF_ELECTION_2(Role.NOT_A_ROLE),
    POPULAR_VOTING(Role.NOT_A_ROLE),
    MUTANT(Role.MUTANT_BASE),
    DOCTOR(Role.DOCTOR),
    PSYCHOLOGIST(Role.PSYCHOLOGIST),
    GENETICIST(Role.GENETICIST),
    COMPUTER_SCIENTIST(Role.COMPUTER_SCIENTIST),
    SPY(Role.SPY),
    DETECTIVE(Role.DETECTIVE),
    HACKER(Role.HACKER);

    private final Role mAssociatedRole;

    TurnType(Role associatedRole) {
        mAssociatedRole = associatedRole;
    }

    public Role getAssociatedRole() {
        return mAssociatedRole;
    }
}
