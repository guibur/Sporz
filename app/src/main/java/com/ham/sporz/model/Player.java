package com.ham.sporz.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Player implements Parcelable {
    private int mDbId;
    private int mId;
    private String mName;
    private String mAbbrev;
    private Role mRole;
    private Genome mGenome;
    private boolean mIsMutant;
    private boolean mIsParalyzed;
    private boolean mIsInfected;

    public Player(int id, String name, String abbrev){
        mId = id;
        mName = name;
        mAbbrev = abbrev;
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

    public int getId() {
        return mId;
    }

    public Role getRole() {
        return mRole;
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
