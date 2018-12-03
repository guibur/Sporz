package com.ham.sporz.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Game implements Parcelable {
    private int mDbId = -1;
    private ArrayList<Player> mPlayers;
    private Turn mCurrentTurn;
    private Turn mLastNightTurn = null;
    private Role mWinner = null;

    public Game(){
        mCurrentTurn = new Turn(TurnType.GAME_INSTANCIATION, 0);
        mPlayers = new ArrayList<>();
    }

    public int addPlayer(String name, String abbrev){
        mPlayers.add(new Player(mPlayers.size() -1, name, abbrev));
        return (mPlayers.size() -1);
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

    public ArrayList<Player> getMutants(){
        ArrayList<Player> l = new ArrayList<>();
        for (Player p: mPlayers){
            if (p.isMutant()){
                l.add(p);
            }
        }
        return l;
    }

    public boolean isGameFinished(){
        int nMutants = getMutants().size();
        if (0 == nMutants){
            mWinner = Role.ASTRONAUT;
        }
        return false;
    }

    protected Game(Parcel in) {
        mDbId = in.readInt();
        mPlayers = in.createTypedArrayList(Player.CREATOR);
        mCurrentTurn = in.readParcelable(Turn.class.getClassLoader());
        mLastNightTurn = in.readParcelable(Turn.class.getClassLoader());
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
        dest.writeParcelable(mCurrentTurn, flags);
        dest.writeParcelable(mLastNightTurn, flags);
    }
}
