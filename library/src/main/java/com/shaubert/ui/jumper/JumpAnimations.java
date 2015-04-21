package com.shaubert.ui.jumper;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable;

public class JumpAnimations implements Parcelable {

    public static final int DEFAULT = -1;

    private int newActEnter = DEFAULT;
    private int curActExit = DEFAULT;
    private int newActExit = DEFAULT;
    private int curActEnter = DEFAULT;

    public JumpAnimations() {
    }

    public int getNewActEnter() {
        return newActEnter;
    }

    public void setNewActEnter(int newActEnter) {
        this.newActEnter = newActEnter;
    }

    public int getCurActExit() {
        return curActExit;
    }

    public void setCurActExit(int curActExit) {
        this.curActExit = curActExit;
    }

    public int getNewActExit() {
        return newActExit;
    }

    public void setNewActExit(int newActExit) {
        this.newActExit = newActExit;
    }

    public int getCurActEnter() {
        return curActEnter;
    }

    public void setCurActEnter(int curActEnter) {
        this.curActEnter = curActEnter;
    }

    public boolean hasAnimationsForForwardTransition() {
        return newActEnter != DEFAULT && curActExit != DEFAULT;
    }

    public boolean hasAnimationsForBackwardTransition() {
        return newActExit != DEFAULT && curActEnter != DEFAULT;
    }

    public void setupForForwardTransition(Activity activity) {
        if (hasAnimationsForForwardTransition()) {
            activity.overridePendingTransition(newActEnter, curActExit);
        }
    }

    public void setupForBackwardTransition(Activity activity) {
        if (hasAnimationsForBackwardTransition()) {
            activity.overridePendingTransition(curActEnter, newActExit);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.newActEnter);
        dest.writeInt(this.curActExit);
        dest.writeInt(this.newActExit);
        dest.writeInt(this.curActEnter);
    }

    private JumpAnimations(Parcel in) {
        this.newActEnter = in.readInt();
        this.curActExit = in.readInt();
        this.newActExit = in.readInt();
        this.curActEnter = in.readInt();
    }

    public static Creator<JumpAnimations> CREATOR = new Creator<JumpAnimations>() {
        public JumpAnimations createFromParcel(Parcel source) {
            return new JumpAnimations(source);
        }

        public JumpAnimations[] newArray(int size) {
            return new JumpAnimations[size];
        }
    };
}
