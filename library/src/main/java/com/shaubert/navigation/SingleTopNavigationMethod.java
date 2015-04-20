package com.shaubert.navigation;

import android.content.Intent;

public class SingleTopNavigationMethod<T extends Bundler> extends OpenNewNavigationMethod<T> {

    public SingleTopNavigationMethod(ActivityStarter activityStarter, Class<?> actClass) {
        super(activityStarter, actClass);
    }

    @Override
    protected void setupIntent(Intent intent) {
        super.setupIntent(intent);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
    }
}
