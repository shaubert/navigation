package com.shaubert.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;

public abstract class AbstractNavigationMethod<T extends Bundler> implements NavigationMethod<T> {

    private ActivityStarter activityStarter;
    private Class<?> actClass;
    private TransitionsBundle transitionsBundle;
    private ActivityOptionsCompat activityOptions;

    protected AbstractNavigationMethod(ActivityStarter activityStarter, Class<?> actClass) {
        this.activityStarter = activityStarter;
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
        if (transitionsBundle != null) {
            extras = NavExtras.setupBackwardTransition(extras, transitionsBundle);
        }
        Intent intent = createIntent(extras);
        Bundle activityOptionsBundle = activityOptions == null ? null : activityOptions.toBundle();
        if (requestCode >= 0) {
            activityStarter.startActivityForResult(intent, requestCode, activityOptionsBundle);
        } else {
            activityStarter.startActivity(intent, activityOptionsBundle);
        }
        if (transitionsBundle != null) {
            Context context = activityStarter.getContext();
            if (context instanceof Activity) {
                transitionsBundle.setupForForwardTransition((Activity) context);
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
        Intent intent = new Intent(activityStarter.getContext(), actClass);
        if (extras != null) {
            intent.putExtras(extras);
        }
        setupIntent(intent);
        return intent;
    }

    protected abstract void setupIntent(Intent intent);

    @Override
    public NavigationMethod<T> setupTransitions(int enter, int exit) {
        transitionsBundle = new TransitionsBundle();
        transitionsBundle.setNewActEnter(enter);
        transitionsBundle.setCurActExit(exit);
        return this;
    }

    @Override
    public NavigationMethod<T> setupTransitions(int newActEnter, int curActExit, int newActExit, int curActEnter) {
        transitionsBundle = new TransitionsBundle();
        transitionsBundle.setNewActEnter(newActEnter);
        transitionsBundle.setCurActExit(curActExit);
        transitionsBundle.setNewActExit(newActExit);
        transitionsBundle.setCurActEnter(curActEnter);
        return this;
    }

    @Override
    public NavigationMethod<T> setupActivityOptions(ActivityOptionsCompat activityOptions) {
        this.activityOptions = activityOptions;
        return this;
    }
}