package com.shaubert.ui.jumper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public abstract class AbstractJumper implements Jumper {

    private Starter starter;
    private boolean paused = true;
    private boolean attached;

    public AbstractJumper(Object fragmentOrContext) {
        if (fragmentOrContext instanceof Fragment) {
            this.starter = new Starter(this, (Fragment) fragmentOrContext);
        } else {
            this.starter = new Starter(this, (Context) fragmentOrContext);
        }
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
        if (!starter.isActivityBased()) {
            return;
        }

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
    public void dispatchOnCreate(Bundle savedInstanceState, Object persistableBundle) {
        if (!starter.isActivityBased()) {
            return;
        }

        Context context = starter.getContext();
        if (context instanceof Activity
                && savedInstanceState == null) {
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

        if (starter.isActivityBased()) {
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
    }

    @Override
    public void dispatchOnSaveInstanceState(@NonNull Bundle bundle) {
    }

    @Override
    public void dispatchOnSavePersistentState(@NonNull Object outPersistableBundle) {
    }

    @Override
    public void dispatchOnStart() {
    }

    @Override
    public void dispatchOnStop(boolean isFinishing) {
    }

    @Override
    public void dispatchOnDestroy() {
    }

    @Override
    public void dispatchOnRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    }

    @Override
    public boolean isPaused() {
        return paused;
    }

}