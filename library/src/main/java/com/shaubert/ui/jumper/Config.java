package com.shaubert.ui.jumper;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;

public class Config extends Args {

    static String CONFIG_ARGS = "__config_args";

    private Intent openIntentAfterStart;
    private boolean finishAfterStart;
    private JumpAnimations jumpAnimations;

    public Config() {
    }

    public Intent getOpenIntentAfterStart() {
        return openIntentAfterStart;
    }

    public Config setOpenIntentAfterStart(Intent intent) {
        this.openIntentAfterStart = intent;
        return this;
    }

    public boolean isFinishAfterStart() {
        return finishAfterStart;
    }

    public Config setFinishAfterStart(boolean finish) {
        this.finishAfterStart = finish;
        return this;
    }

    public JumpAnimations getJumpAnimations() {
        return jumpAnimations;
    }

    public Config setActivityAnimations(JumpAnimations jumpAnimations) {
        this.jumpAnimations = jumpAnimations;
        return this;
    }

    public Config createChildActivityConfig() {
        return new Config();
    }

    public void mergeParentConfig(Config config) {
    }

    @Override
    public final Bundle toBundle() {
        return toBundle(null);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.openIntentAfterStart, flags);
        dest.writeByte(this.finishAfterStart ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.jumpAnimations, flags);
    }

    protected Config(Parcel in) {
        this.openIntentAfterStart = in.readParcelable(Intent.class.getClassLoader());
        this.finishAfterStart = in.readByte() != 0;
        this.jumpAnimations = in.readParcelable(JumpAnimations.class.getClassLoader());
    }

    public static final Creator<Config> CREATOR = new Creator<Config>() {
        @Override
        public Config createFromParcel(Parcel source) {
            return new Config(source);
        }

        @Override
        public Config[] newArray(int size) {
            return new Config[size];
        }
    };
}