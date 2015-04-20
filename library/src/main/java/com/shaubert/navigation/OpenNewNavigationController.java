package com.shaubert.navigation;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class OpenNewNavigationController implements NavigationController {

    public static final String TAG = OpenNewNavigationController.class.getSimpleName();

    private ActivityStarter activityStarter;

    public OpenNewNavigationController(Context context) {
        this.activityStarter = new ActivityStarter(context);
    }

    public OpenNewNavigationController(Fragment fragment) {
        this.activityStarter = new ActivityStarter(fragment);
    }

    public OpenNewNavigationController(android.support.v4.app.Fragment fragment) {
        this.activityStarter = new ActivityStarter(fragment);
    }

    @Override
    public void clearTop(Intent rootIntent, boolean finishCurrent) {
        if (finishCurrent) activityStarter.finishActivity();
        if (rootIntent == null) {
            return;
        }

        try {
            Class<?> actClazz = Class.forName(rootIntent.getComponent().getClassName());
            new ClearTopNavigationMethod<Bundler.VoidBundle>(activityStarter, actClazz).start();
        } catch (ClassNotFoundException ex) {
            Log.e(TAG, "failed to resolve activity class", ex);
        }
    }

    @Override
    public NavigationMethod<Bundler> getMethodByClass(Class<?> actClass) {
        return new OpenNewNavigationMethod<>(activityStarter, actClass);
    }

}