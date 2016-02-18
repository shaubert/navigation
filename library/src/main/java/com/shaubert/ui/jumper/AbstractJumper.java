package com.shaubert.ui.jumper;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;

public abstract class AbstractJumper implements Jumper {

    private Starter starter;
    private boolean paused = true;
    private boolean attached;

    public AbstractJumper(Context context) {
        this.starter = new Starter(this, context);
    }

    public AbstractJumper(Fragment fragment) {
        this.starter = new Starter(this, fragment);
    }

    public AbstractJumper(android.support.v4.app.Fragment fragment) {
        this.starter = new Starter(this, fragment);
    }

    public Starter getStarter() {
        return starter;
    }

    @Override
    public <T extends Args> T getArgs(Intent intent) {
        return Args.fromArgs(intent);
    }

    @Override
    public <T extends Config> T getConfig(Intent intent) {
        if (intent == null) return null;

        Bundle extras = intent.getExtras();
        if (extras == null) return null;

        Bundle bundle = extras.getBundle(Config.CONFIG_ARGS);
        if (bundle == null) return null;

        return Args.fromArgs(bundle);
    }

    @Override
    public void handleIntent(Intent intent) {
        Config config = getConfig(intent);
        if (config == null) return;

        if (config.isFinishAfterStart()) {
            starter.finishActivity();
        }

        Intent openIntentAfterStart = config.getOpenIntentAfterStart();
        if (openIntentAfterStart != null) {
            starter.startActivity(openIntentAfterStart, null);
        }
    }

    @Override
    public boolean isAttached() {
        return attached;
    }

    @Override
    public void dispatchOnAttach() {
        attached = true;
    }

    @Override
    public void dispatchOnDetach() {
        attached = false;
    }

    @Override
    public void dispatchOnCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        Context context = starter.getContext();
        if (context instanceof Activity) {
            handleIntent(((Activity) context).getIntent());
        }
    }

    @Override
    public void dispatchOnActivityResult(int requestCode, int resultCode, Intent data) {
    }

    @Override
    public void dispatchOnResume() {
        paused = false;
    }

    @Override
    public void dispatchOnPause(boolean isFinishing) {
        paused = true;

        Context context = starter.getContext();
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            Config config = getConfig(activity.getIntent());
            if (config != null && activity.isFinishing()) {
                JumpAnimations animations = config.getJumpAnimations();
                if (animations != null) {
                    animations.setupForBackwardTransition(activity);
                }
            }
        }
    }

    @Override
    public void dispatchOnSaveInstanceState(@NonNull Bundle bundle) {

    }

    @Override
    public boolean isPaused() {
        return paused;
    }

}