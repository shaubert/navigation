package com.shaubert.ui.jumper;

import android.content.Intent;
import android.os.Bundle;

public class Config extends Args {

    public static final String JUMP_ARGS = "__config_args";

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
    public final void fromBundle(Bundle bundle) {
        if (bundle == null) return;
        super.fromBundle(bundle.getBundle(JUMP_ARGS));
    }

    @Override
    public final Bundle toBundle(Bundle out) {
        if (out == null) {
            out = new Bundle();
        }
        out.putBundle(JUMP_ARGS, super.toBundle());
        return out;
    }

    @Override
    public final Bundle toBundle() {
        return toBundle(null);
    }

}