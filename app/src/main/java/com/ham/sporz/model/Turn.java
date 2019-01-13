package com.ham.sporz.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.ham.sporz.model.enums.TurnType;

public class Turn implements Parcelable {
    private int mDbId;
    private TurnType mType;
    private int mSource;
    private int mTarget;

    public Turn(TurnType type){
        mType = type;
    }

    public TurnType getType() {
        return mType;
    }

    public int getTarget() {
        return mTarget;
    }

    public void setTarget(int target){
        mTarget = target;
    }

    public void setSourceTarget(int source, int target){
        mSource = source;
        mTarget = target;
    }

    protected Turn(Parcel in) {
        mDbId = in.readInt();
        mType = TurnType.valueOf(in.readString());
        mSource = in.readInt();
        mTarget = in.readInt();
    }

    public static final Creator<Turn> CREATOR = new Creator<Turn>() {
        @Override
        public Turn createFromParcel(Parcel in) {
            return new Turn(in);
        }

        @Override
        public Turn[] newArray(int size) {
            return new Turn[size];
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
