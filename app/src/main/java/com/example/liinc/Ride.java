package com.example.liinc;

import android.os.Parcel;
import android.os.Parcelable;

public class Ride implements Parcelable {
    String mGoingFrom;
    String mGoingTo;
    String mDate;
    String mTime;
    int mNumOfPassengers;
    String mVehicleName;

    public Ride() {
        // Default constructor required for Firebase
    }

    public Ride(String goingFrom, String goingTo, String date, String time, int numOfPassengers, String vehicleName) {
        mGoingFrom = goingFrom;
        mGoingTo = goingTo;
        mDate = date;
        mTime = time;
        mNumOfPassengers = numOfPassengers;
        mVehicleName = vehicleName;
    }

    public String getGoingFrom() {
        return mGoingFrom;
    }

    public void setGoingFrom(String goingFrom) {
        mGoingFrom = goingFrom;
    }

    public String getGoingTo() {
        return mGoingTo;
    }

    public void setGoingTo(String goingTo) {
        mGoingTo = goingTo;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public int getNumOfPassengers() {
        return mNumOfPassengers;
    }

    public void setNumOfPassengers(int numOfPassengers) {
        mNumOfPassengers = numOfPassengers;
    }

    public String getVehicleName() {
        return mVehicleName;
    }

    public void setVehicleName(String vehicleName) {
        mVehicleName = vehicleName;
    }

    // Parcelable implementation

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mGoingFrom);
        dest.writeString(mGoingTo);
        dest.writeString(mDate);
        dest.writeString(mTime);
        dest.writeInt(mNumOfPassengers);
        dest.writeString(mVehicleName);
    }

    private Ride(Parcel in) {
        mGoingFrom = in.readString();
        mGoingTo = in.readString();
        mDate = in.readString();
        mTime = in.readString();
        mNumOfPassengers = in.readInt();
        mVehicleName = in.readString();
    }

    public static final Creator<Ride> CREATOR = new Creator<Ride>() {
        @Override
        public Ride createFromParcel(Parcel in) {
            return new Ride(in);
        }

        @Override
        public Ride[] newArray(int size) {
            return new Ride[size];
        }
    };
}