package com.shaubert.navigation;

import android.content.Intent;

public class OpenNewNavigationMethod<T extends Bundler> extends AbstractNavigationMethod<T> {

    public OpenNewNavigationMethod(ActivityStarter activityStarter, Class<?> actClass) {
        super(activityStarter, actClass);
    }

    @Override
    protected void setupIntent(Intent intent) {
    }

}