package com.ham.sporz.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Action implements Parcelable {
    private int mDbId;
    private ActionType mType;
    private int mSource;
    private int mTarget;

    protected Action(Parcel in) {
        mDbId = in.readInt();
        mSource = in.readInt();
        mTarget = in.readInt();
    }

    public static final Creator<Action> CREATOR = new Creator<Action>() {
        @Override
        public Action createFromParcel(Parcel in) {
            return new Action(in);
        }

        @Override
        public Action[] newArray(int size) {
            return new Action[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mDbId);
        dest.writeInt(mSource);
        dest.writeInt(mTarget);
    }
}