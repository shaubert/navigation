package com.shaubert.ui.jumper;

import android.os.Parcel;

public final class VoidArgs extends Args {

    private VoidArgs() {
    }

    private VoidArgs(Parcel in) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    public static final Creator<VoidArgs> CREATOR = new Creator<VoidArgs>() {
        @Override
        public VoidArgs createFromParcel(Parcel source) {
            return new VoidArgs(source);
        }

        @Override
        public VoidArgs[] newArray(int size) {
            return new VoidArgs[size];
        }
    };
}
