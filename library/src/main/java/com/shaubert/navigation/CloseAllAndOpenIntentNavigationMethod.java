package com.shaubert.navigation;

import android.app.Activity;
import android.content.Intent;

public class CloseAllAndOpenIntentNavigationMethod<T extends Bundler> extends ClearTopNavigationMethod<T> {
    private Intent openIntent;

    public CloseAllAndOpenIntentNavigationMethod(ActivityStarter activityStarter, Class<? extends Activity> baseActivityClass, Intent openIntent) {
        super(activityStarter, baseActivityClass);
        this.openIntent = openIntent;
    }

    @Override
    protected void setupIntent(Intent intent) {
        super.setupIntent(intent);
        NavExtras.setupCloseAllAndOpenActivityIntent(intent, openIntent);
    }
}
