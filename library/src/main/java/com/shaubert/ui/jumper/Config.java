package com.shaubert.ui.jumper;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;

public class Config extends Args {

    static String CONFIG_ARGS = "__config_args";

    protected Intent openIntentAfterStart;
    protected boolean finishAfterStart;
    protected boolean useUpInActionBarAsFinish;
    protected boolean removeUpFromActionBar;
    protected JumpAnimations jumpAnimations;

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

    public boolean isUseUpInActionBarAsFinish() {
        return useUpInActionBarAsFinish;
    }

    public Config setUseUpInActionBarAsFinish(boolean upAsFinish) {
        this.useUpInActionBarAsFinish = upAsFinish;
        return this;
    }

    public boolean isRemoveUpFromActionBar() {
        return removeUpFromActionBar;
    }

    public Config setRemoveUpFromActionBar(boolean remove) {
        this.removeUpFromActionBar = remove;
        return this;
    }

    public JumpAnimations getJumpAnimations() {
        return jumpAnimations;
    }

    public Config setActivityAnimations(JumpAnimations jumpAnimations) {
        this.jumpAnimations = jumpAnimations;
        return this;
    }

    public Config createChildAcitivityConfig() {
        Config config = new Config();
        config.setUseUpInActionBarAsFinish(useUpInActionBarAsFinish);
        return config;
    }

    public void mergeParentConfig(Config config) {
        if (config.isUseUpInActionBarAsFinish()) {
            setUseUpInActionBarAsFinish(true);
        }
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
        dest.writeByte(this.useUpInActionBarAsFinish ? (byte) 1 : (byte) 0);
        dest.writeByte(this.removeUpFromActionBar ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.jumpAnimations, flags);
    }

    protected Config(Parcel in) {
        this.openIntentAfterStart = in.readParcelable(Intent.class.getClassLoader());
        this.finishAfterStart = in.readByte() != 0;
        this.useUpInActionBarAsFinish = in.readByte() != 0;
        this.removeUpFromActionBar = in.readByte() != 0;
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