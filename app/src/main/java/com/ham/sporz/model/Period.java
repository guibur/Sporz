package com.ham.sporz.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.ham.sporz.model.enums.TurnType;
import com.ham.sporz.model.enums.PeriodType;

import java.util.ArrayList;

public class Period implements Parcelable {
    private int mDbId = -1;
    private final PeriodType mType;
    private final int mNumber;
    private ArrayList<Turn> mListTurns;

    public Period(PeriodType type, int number, TurnType initialAction){
        mType = type;
        mNumber = number;
        mListTurns = new ArrayList<>();
        mListTurns.add(new Turn(initialAction));
    }

    public Turn getCurrentTurn(){
        return mListTurns.get(mListTurns.size() - 1);
    }

    public void setNewTurn(TurnType type){
        mListTurns.add(new Turn(type));
    }

    public PeriodType getType() {
        return mType;
    }

    public int getNumber() {
        return mNumber;
    }

    protected Period(Parcel in) {
        mDbId = in.readInt();
        mType = PeriodType.valueOf(in.readString());
        mNumber = in.readInt();
        mListTurns = in.createTypedArrayList(Turn.CREATOR);
    }

    public static final Creator<Period> CREATOR = new Creator<Period>() {
        @Override
        public Period createFromParcel(Parcel in) {
            return new Period(in);
        }

        @Override
        public Period[] newArray(int size) {
            return new Period[size];
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
        dest.writeTypedList(mListTurns);
    }
}
