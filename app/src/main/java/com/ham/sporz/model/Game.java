package com.ham.sporz.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.ham.sporz.model.enums.TurnType;
import com.ham.sporz.model.enums.Genome;
import com.ham.sporz.model.enums.PeriodType;
import com.ham.sporz.model.enums.Role;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Game implements Parcelable, Cloneable {
    private static final String TAG = "Game Model";

    private int mDbId = -1;
    private ArrayList<Player> mPlayers = new ArrayList<>();
    private LinkedList<Turn> mTurnList = new LinkedList<>();
    private int mChief = -1;
    private Role mWinner = Role.NOT_A_ROLE;

    private final static ArrayList<TurnType> TURN_ORDER = new ArrayList<TurnType>(){{
        add(TurnType.CHIEF_ELECTION);
        add(TurnType.POPULAR_VOTING);
        add(TurnType.CHIEF_ELECTION_2);
        add(TurnType.MUTANT);
        add(TurnType.DOCTOR);
        add(TurnType.PSYCHOLOGIST);
        add(TurnType.GENETICIST);
        add(TurnType.COMPUTER_SCIENTIST);
        add(TurnType.SPY);
        add(TurnType.DETECTIVE);
        add(TurnType.HACKER);
    }};

    public Game(){
        mTurnList.add(new Turn(TurnType.GAME_INSTANTIATION, PeriodType.GAME_INSTANCIATION, 0, false));
    }

    @Override
    public Game clone() throws CloneNotSupportedException {
        Parcel p = Parcel.obtain();
        p.writeValue(this);
        p.setDataPosition(0);
        Game clonedGame = (Game)p.readValue(Game.class.getClassLoader());
        p.recycle();
        return clonedGame;
    }

    public int addPlayer(String name){
        mPlayers.add(new Player(mPlayers.size(), name));
        return (mPlayers.size() -1);
    }

    public void setPlayer(int idx, Role role, Genome genome){
        mPlayers.get(idx).setRoleGenome(role, genome);
        if (Role.MUTANT_BASE == role){
            mPlayers.get(idx).mutate();
        }
    }

    public ArrayList<Player> getPlayers()
    {
        return mPlayers;
    }

    public Player getPlayer(int i){
        return mPlayers.get(i);
    }

    public ArrayList<Player> getPlayers(Role r){
        ArrayList<Player> l = new ArrayList<>();
        for (Player p: mPlayers){
            if (r == p.getRole()){
                l.add(p);
            }
        }
        return l;
    }

    public ArrayList<Player> getAlivePlayers(Role r){
        ArrayList<Player> l = new ArrayList<>();
        for (Player p: mPlayers){
            if (r == p.getRole() && p.isAlive()){
                l.add(p);
            }
        }
        return l;
    }

    public boolean canPlay(Role r){
        ArrayList<Player> l = getAlivePlayers(r);
        for (Player p: l){
            if (! p.isParalyzed()){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Player> getAlivePlayers(Genome g){
        ArrayList<Player> l = new ArrayList<>();
        for (Player p: mPlayers){
            if (g == p.getGenome() && p.isAlive()){
                l.add(p);
            }
        }
        return l;
    }

    public ArrayList<Player> getMutants(){
        ArrayList<Player> l = new ArrayList<>();
        for (Player p: mPlayers){
            if (p.isMutant()){
                l.add(p);
            }
        }
        return l;
    }

    public boolean hasChief(){
        if (-1 == mChief || getPlayer(mChief).isDead())
            return false;
        else
            return true;
    }

    public void setChief(int newChief){
        mChief = newChief;
        getPlayer(newChief).setChief();
    }

    public int getChief(){
        return mChief;
    }

    public boolean isGameFinished(){
        int nMutants = getMutants().size();
        if (0 == nMutants){
            mWinner = Role.ASTRONAUT;
            return true;
        }
        int nDoctors = getAlivePlayers(Role.DOCTOR).size();
        int nResistant = getAlivePlayers(Genome.RESISTANT).size();
        if (0 == nDoctors && 1 < nMutants && 0 == nResistant){
            mWinner = Role.MUTANT_BASE;
            return true;
        }
        return false;
    }

    public Turn getCurrentTurn(){
        return mTurnList.getLast();
    }

    public LinkedList<Turn> getCurrentNightTurns(){
        LinkedList<Turn> currentNightTurns = new LinkedList<>();
        Iterator<Turn> turnIt = mTurnList.descendingIterator();
        while (turnIt.hasNext()){
            Turn turn = turnIt.next();
            if (turn.getPeriodType() == PeriodType.DAY)
                break;
            currentNightTurns.addFirst(turn);
        }
        return currentNightTurns;
    }

    public LinkedList<Turn> getLastNightTurns(){
        LinkedList<Turn> lastNightTurns = new LinkedList<>();
        if (getCurrentTurn().getPeriodNumber() == 0){
            return lastNightTurns; // empty as there is no previous night.
        }
        Iterator<Turn> turnIt = mTurnList.descendingIterator();
        boolean nightPeriodFinished = false;
        boolean dayPeriodFinished = false;
        while (turnIt.hasNext()){
            Turn turn = turnIt.next();
            if (!nightPeriodFinished && turn.getPeriodType() == PeriodType.DAY)
                nightPeriodFinished = true;
            else if (!dayPeriodFinished && turn.getPeriodType() == PeriodType.NIGHT){
                dayPeriodFinished = true;
                lastNightTurns.addFirst(turn);
            }
            else{
                if (turn.getPeriodType() == PeriodType.DAY)
                    break;
                lastNightTurns.addFirst(turn);
            }
        }
        return lastNightTurns;
    }

    public void setNewTurn(TurnType turnType, PeriodType periodType, int periodNumber, boolean isParalysed){
        mTurnList.addLast(new Turn(turnType, periodType, periodNumber, isParalysed));
    }

    public void setNewTurn(){
        if (TurnType.GAME_INSTANTIATION == getCurrentTurn().getType()){
            mTurnList.addLast(new Turn(TurnType.CHIEF_ELECTION_2, PeriodType.DAY, 0, false));
            return;
        }

        int nextTypeIdx = TURN_ORDER.indexOf(getCurrentTurn().getType());
        Log.e(TAG, "Current turn = " + String.valueOf(nextTypeIdx));
        TurnType nextType = null;
        boolean canNextTurnPlay = false;
        PeriodType nextPeriodType = getCurrentTurn().getPeriodType();
        int nextPeriodNumber = getCurrentTurn().getPeriodNumber();
        while (null == nextType){
            ++nextTypeIdx;
            if (nextTypeIdx == TURN_ORDER.size()){
                nextTypeIdx = 0;
            }
            TurnType potentialNextType = TURN_ORDER.get(nextTypeIdx);
            switch (potentialNextType){
                case CHIEF_ELECTION:
                    nextPeriodType = PeriodType.DAY;
                    ++nextPeriodNumber;
                    if (mChief < 0 || mPlayers.get(mChief).isDead())
                        nextType = TurnType.CHIEF_ELECTION;
                    break;
                case POPULAR_VOTING:
                    nextType = TurnType.POPULAR_VOTING;
                    break;
                case CHIEF_ELECTION_2:
                    if (mChief < 0 || mPlayers.get(mChief).isDead())
                        nextType = TurnType.CHIEF_ELECTION_2;
                    break;
                case MUTANT:
                    nextPeriodType = PeriodType.NIGHT;
                    nextType = TurnType.MUTANT;
                    break;
                case DETECTIVE:
                    // Don't play the detective on first night as there is no previous night.
                    if (nextPeriodNumber == 0){
                        break;
                    }
                case DOCTOR:
                case GENETICIST:
                case PSYCHOLOGIST:
                case COMPUTER_SCIENTIST:
                case SPY:
                case HACKER:
                    if (getAlivePlayers(potentialNextType.getAssociatedRole()).size() > 0){
                        nextType = potentialNextType;
                        canNextTurnPlay = canPlay(potentialNextType.getAssociatedRole());
                    }
                    break;
                case GAME_INSTANTIATION:
                default:
                    // Do nothing.
            }
        }
        mTurnList.addLast(new Turn(nextType, nextPeriodType, nextPeriodNumber, canNextTurnPlay));
    }

    protected Game(Parcel in) {
        mDbId = in.readInt();
        mPlayers = in.createTypedArrayList(Player.CREATOR);
        mTurnList = new LinkedList<>(in.createTypedArrayList(Turn.CREATOR));
        mChief = in.readInt();
        mWinner = Role.valueOf(in.readString());
    }

    public static final Creator<Game> CREATOR = new Creator<Game>() {
        @Override
        public Game createFromParcel(Parcel in) {
            return new Game(in);
        }

        @Override
        public Game[] newArray(int size) {
            return new Game[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mDbId);
        dest.writeTypedList(mPlayers);
        dest.writeTypedList(mTurnList);
        dest.writeInt(mChief);
        dest.writeString(mWinner.name());
    }
}
