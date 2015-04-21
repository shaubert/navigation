package com.shaubert.ui.jumper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;

public interface Jump<T extends Args> {

    void start();

    void start(Bundle extras);

    void start(T extras);

    void startForResult(int requestCode);

    void startForResult(int requestCode, Bundle extras);

    void startForResult(int requestCode, T extras);

    Intent getIntent();

    Intent getIntent(Bundle extras);

    Jump<T> withActivityOptions(ActivityOptionsCompat activityOptions);

    Jump<T> withAnimations(int enter, int exit);

    Jump<T> withAnimations(int newActivityEnter, int curActivityExit, int newActivityExit, int curActivityEnter);

}