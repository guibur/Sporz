package com.ham.sporz.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.ham.sporz.model.enums.TurnType;

import java.util.ArrayList;

public class Turn implements Parcelable {
    private int mDbId = -1;
    private final TurnType mType;
    private final int mNumber;
    private ArrayList<Action> mListActions;

    public Turn(TurnType type, int number){
        mType = type;
        mNumber = number;
        mListActions = new ArrayList<>();
    }

    protected Turn(Parcel in) {
        mDbId = in.readInt();
        mType = TurnType.valueOf(in.readString());
        mNumber = in.readInt();
        mListActions = in.createTypedArrayList(Action.CREATOR);
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
        dest.writeInt(mNumber);
        dest.writeTypedList(mListActions);
    }
}
