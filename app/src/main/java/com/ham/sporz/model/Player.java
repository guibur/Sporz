package com.ham.sporz.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.ham.sporz.model.enums.Genome;
import com.ham.sporz.model.enums.Role;

import java.util.ArrayList;

public class Player implements Parcelable {
    private int mDbId;
    private int mId;
    private String mName;
    private Role mRole = Role.ASTRONAUT;
    private Genome mGenome = Genome.NORMAL;
    private boolean mIsMutant = false;
    private boolean mIsParalyzed = false;
    private boolean mIsInfected = false;
    private boolean mIsDead = false;
    private boolean mIsChief = false;

    public Player(int id, String name){
        mId = id;
        mName = name;
    }

    public void setChief(){
        mIsChief = true;
    }

    public boolean isChief() {
        return mIsChief;
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

    public boolean isAlive(){
        return !mIsDead;
    }
    public boolean isDead(){
        return mIsDead;
    }

    public boolean isParalyzed() {
        return mIsParalyzed;
    }

    public boolean isInfected() {
        return mIsInfected;
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
        mId = in.readInt();
        mName = in.readString();
        mRole = Role.valueOf(in.readString());
        mGenome = Genome.valueOf(in.readString());
        mIsMutant = in.readByte() != 0;
        mIsParalyzed = in.readByte() != 0;
        mIsInfected = in.readByte() != 0;
        mIsDead = in.readByte() != 0;
        mIsChief = in.readByte() != 0;
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
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeString(mRole.name());
        dest.writeString(mGenome.name());
        dest.writeByte((byte) (mIsMutant ? 1 : 0));
        dest.writeByte((byte) (mIsParalyzed ? 1 : 0));
        dest.writeByte((byte) (mIsInfected ? 1 : 0));
        dest.writeByte((byte) (mIsDead ? 1 : 0));
        dest.writeByte((byte) (mIsChief ? 1 : 0));
    }
}
