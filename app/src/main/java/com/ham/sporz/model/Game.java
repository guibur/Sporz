package com.ham.sporz.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.ham.sporz.model.enums.TurnType;
import com.ham.sporz.model.enums.Genome;
import com.ham.sporz.model.enums.PeriodType;
import com.ham.sporz.model.enums.Role;

import java.util.ArrayList;

public class Game implements Parcelable {
    private int mDbId = -1;
    private ArrayList<Player> mPlayers;
    private Period mCurrentPeriod;
    private Period mLastNightPeriod = null;
    private int mChief = -1;
    private Role mWinner = Role.NOT_A_ROLE;

    public Game(){
        mCurrentPeriod = new Period(PeriodType.GAME_INSTANCIATION, 0, TurnType.GAME_INSTANCIATION);
        mPlayers = new ArrayList<>();
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

    public Period getCurrentPeriod() {
        return mCurrentPeriod;
    }

    public void setNewTurn(PeriodType type, int number, TurnType initialAction){
        if (null != mCurrentPeriod && PeriodType.NIGHT == mCurrentPeriod.getType())
            mLastNightPeriod = mCurrentPeriod;
        mCurrentPeriod = new Period(type, number, initialAction);
//        int nextNumber = 0;
//        PeriodType nextType = null;
//        switch (mCurrentPeriod.getType()){
//            case NIGHT:
//                mLastNightPeriod = mCurrentPeriod;
//                nextType = PeriodType.DAY;
//                nextNumber = mCurrentPeriod.getNumber() + 1;
//                break;
//            case GAME_INSTANCIATION:
//                nextNumber = mCurrentPeriod.getNumber();
//                nextType = PeriodType.DAY;
//                break;
//            case DAY:
//                nextNumber = mCurrentPeriod.getNumber();
//                nextType = PeriodType.NIGHT;
//                break;
//        }
//        mCurrentPeriod = new Period(nextType, nextNumber, hasChief());
    }

    protected Game(Parcel in) {
        mDbId = in.readInt();
        mPlayers = in.createTypedArrayList(Player.CREATOR);
        mCurrentPeriod = in.readParcelable(Period.class.getClassLoader());
        mLastNightPeriod = in.readParcelable(Period.class.getClassLoader());
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
        dest.writeParcelable(mCurrentPeriod, flags);
        dest.writeParcelable(mLastNightPeriod, flags);
        dest.writeInt(mChief);
        dest.writeString(mWinner.name());
    }
}
