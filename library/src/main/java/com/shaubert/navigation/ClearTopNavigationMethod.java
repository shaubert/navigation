package com.shaubert.navigation;

import android.content.Intent;

public class ClearTopNavigationMethod<T extends Bundler> extends SingleTopNavigationMethod<T> {

    public ClearTopNavigationMethod(ActivityStarter activityStarter, Class<?> actClass) {
        super(activityStarter, actClass);
    }

    @Override
    protected void setupIntent(Intent intent) {
        super.setupIntent(intent);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }
}
