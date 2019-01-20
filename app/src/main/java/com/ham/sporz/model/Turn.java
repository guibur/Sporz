package com.ham.sporz.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.ham.sporz.model.enums.ActionType;
import com.ham.sporz.model.enums.PeriodType;
import com.ham.sporz.model.enums.TurnType;

import java.util.ArrayList;

public class Turn implements Parcelable {
    private int mDbId = -1;
    private TurnType mType;
    private PeriodType mPeriodType;
    private int mPeriodNumber;
    private boolean mIsParalysed;
    private ArrayList<Action> mActionList;


    public Turn(TurnType type, PeriodType periodType, int periodNumber, boolean isParalysed){
        mType = type;
        mPeriodType = periodType;
        mPeriodNumber = periodNumber;
        mIsParalysed = isParalysed;
        mActionList = new ArrayList<>();
    }

    public void setNewAction(ActionType type){
        mActionList.add(new Action(type));
    }

    public int getActionNumber(){
        return mActionList.size();
    }


    public void setNewAction(ActionType type, int target){
        mActionList.add(new Action(type, target));
    }


    public void setNewAction(ActionType type, int source, int target){
        mActionList.add(new Action(type, source, target));
    }

    public Action getAction(int index){
        return mActionList.get(index);
    }

    public int getPeriodNumber() {
        return mPeriodNumber;
    }

    public PeriodType getPeriodType(){
        return mPeriodType;
    }

    public TurnType getType() {
        return mType;
    }

    protected Turn(Parcel in) {
        mDbId = in.readInt();
        mType = TurnType.valueOf(in.readString());
        mPeriodType = PeriodType.valueOf(in.readString());
        mPeriodNumber = in.readInt();
        mIsParalysed = (in.readByte() != 0);
        mActionList = in.createTypedArrayList(Action.CREATOR);
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
        dest.writeString(mPeriodType.name());
        dest.writeInt(mPeriodNumber);
        dest.writeByte((byte) (mIsParalysed ? 1 : 0) );
        dest.writeTypedList(mActionList);
    }
}
