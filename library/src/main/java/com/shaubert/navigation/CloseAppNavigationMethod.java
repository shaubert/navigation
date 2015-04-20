package com.shaubert.navigation;

import android.app.Activity;
import android.content.Intent;

public class CloseAppNavigationMethod<T extends Bundler> extends ClearTopNavigationMethod<T> {
    public CloseAppNavigationMethod(ActivityStarter activityStarter, Class<? extends Activity> baseActivityClass) {
        super(activityStarter, baseActivityClass);
    }

    @Override
    protected void setupIntent(Intent intent) {
        super.setupIntent(intent);
        NavExtras.setupCloseAppIntent(intent);
    }
}
