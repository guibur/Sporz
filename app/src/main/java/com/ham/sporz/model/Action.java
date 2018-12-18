package com.ham.sporz.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.ham.sporz.model.enums.ActionType;

public class Action implements Parcelable {
    private int mDbId;
    private ActionType mType;
    private int mSource;
    private int mTarget;

    protected Action(Parcel in) {
        mDbId = in.readInt();
        mType = ActionType.valueOf(in.readString());
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
        dest.writeString(mType.name());
        dest.writeInt(mSource);
        dest.writeInt(mTarget);
    }
}
