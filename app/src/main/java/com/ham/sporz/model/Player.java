package com.ham.sporz.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {
    private int mDbId;
    private int mId;
    private String mName;
    private String mAbbrev;
    private Role mRole = Role.ASTRONAUT;
    private Genome mGenome = Genome.NORMAL;
    private boolean mIsMutant = false;
    private boolean mIsParalyzed = false;
    private boolean mIsInfected = false;
    private boolean mIsDead = false;

    public Player(int id, String name, String abbrev){
        mId = id;
        mName = name;
        mAbbrev = abbrev;
    }

    public void setRoleGenome(Role role, Genome genome){
        mRole = role;
        mGenome = genome;
    }

    public void mutate(){
        if (mGenome != Genome.RESISTANT){
            mIsMutant = true;
        }
    }

    public void heal(){
        if (mGenome != Genome.HOST) {
            mIsMutant = false;
        }
    }

    public void paralyze(){
        mIsParalyzed = true;
    }

    public void infect(){
        mIsInfected = true;
    }

    public void kill(){
        mIsDead = true;
    }

    public boolean isMutant() {
        return mIsMutant;
    }

    public String getName() {
        return mName;
    }

    public String getAbbrev() {
        return mAbbrev;
    }

    public boolean isAlive(){
        return !mIsDead;
    }

    public int getId() {
        return mId;
    }

    public Role getRole() {
        return mRole;
    }

    public Genome getGenome() {
        return mGenome;
    }

    protected Player(Parcel in) {
        mDbId = in.readInt();
        mName = in.readString();
        mAbbrev = in.readString();
        mIsMutant = in.readByte() != 0;
        mIsParalyzed = in.readByte() != 0;
        mIsInfected = in.readByte() != 0;
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mDbId);
        dest.writeString(mName);
        dest.writeString(mAbbrev);
        dest.writeByte((byte) (mIsMutant ? 1 : 0));
        dest.writeByte((byte) (mIsParalyzed ? 1 : 0));
        dest.writeByte((byte) (mIsInfected ? 1 : 0));
    }
}
