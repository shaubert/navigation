package com.shaubert.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;

public interface NavigationMethod<T extends Bundler> {

    void start();

    void start(Bundle extras);

    void start(T extras);

    void startForResult(int requestCode);

    void startForResult(int requestCode, Bundle extras);

    void startForResult(int requestCode, T extras);

    Intent getIntent();

    Intent getIntent(Bundle extras);

    NavigationMethod<T> setupActivityOptions(ActivityOptionsCompat activityOptions);

    NavigationMethod<T> setupTransitions(int enter, int exit);

    NavigationMethod<T> setupTransitions(int newActEnter, int curActExit, int newActExit, int curActEnter);

}