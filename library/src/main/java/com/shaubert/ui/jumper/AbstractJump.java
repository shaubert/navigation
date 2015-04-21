package com.shaubert.ui.jumper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;

public abstract class AbstractJump<T extends Args> implements Jump<T> {

    private Starter starter;
    private Class<?> actClass;
    private JumpAnimations jumpAnimations;
    private ActivityOptionsCompat activityOptions;

    protected AbstractJump(Starter starter, Class<?> actClass) {
        this.starter = starter;
        this.actClass = actClass;
    }

    public Class<?> getActClass() {
        return actClass;
    }

    @Override
    public void start() {
        start((Bundle) null);
    }

    @Override
    public void start(T extras) {
        if (extras == null) {
            start((Bundle) null);
            return;
        }

        start(extras.toBundle());
    }

    @Override
    public void start(Bundle extras) {
        startForResult(-1, extras);
    }

    @Override
    public void startForResult(int requestCode) {
        startForResult(requestCode, (Bundle) null);
    }

    @Override
    public void startForResult(int requestCode, T extras) {
        if (extras == null) {
            startForResult(requestCode, (Bundle) null);
            return;
        }

        startForResult(requestCode, extras.toBundle());
    }

    @Override
    public void startForResult(int requestCode, Bundle extras) {
        if (jumpAnimations != null) {
            extras = Extras.setupBackwardAnimations(extras, jumpAnimations);
        }
        Intent intent = createIntent(extras);
        Bundle activityOptionsBundle = activityOptions == null ? null : activityOptions.toBundle();
        if (requestCode >= 0) {
            starter.startActivityForResult(intent, requestCode, activityOptionsBundle);
        } else {
            starter.startActivity(intent, activityOptionsBundle);
        }
        if (jumpAnimations != null) {
            Context context = starter.getContext();
            if (context instanceof Activity) {
                jumpAnimations.setupForForwardTransition((Activity) context);
            }
        }
    }

    @Override
    public Intent getIntent() {
        return createIntent(null);
    }

    @Override
    public Intent getIntent(Bundle extras) {
        return createIntent(extras);
    }

    protected Intent createIntent(Bundle extras) {
        Intent intent = new Intent(starter.getContext(), actClass);
        if (extras != null) {
            intent.putExtras(extras);
        }
        setupIntent(intent);
        return intent;
    }

    protected abstract void setupIntent(Intent intent);

    @Override
    public Jump<T> withAnimations(int enter, int exit) {
        jumpAnimations = new JumpAnimations();
        jumpAnimations.setNewActEnter(enter);
        jumpAnimations.setCurActExit(exit);
        return this;
    }

    @Override
    public Jump<T> withAnimations(int newActivityEnter, int curActivityExit, int newActivityExit, int curActivityEnter) {
        jumpAnimations = new JumpAnimations();
        jumpAnimations.setNewActEnter(newActivityEnter);
        jumpAnimations.setCurActExit(curActivityExit);
        jumpAnimations.setNewActExit(newActivityExit);
        jumpAnimations.setCurActEnter(curActivityEnter);
        return this;
    }

    @Override
    public Jump<T> withActivityOptions(ActivityOptionsCompat activityOptions) {
        this.activityOptions = activityOptions;
        return this;
    }
}