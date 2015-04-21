package com.shaubert.ui.jumper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;

public interface Jump<T extends Args> {

    Intent getIntent();

    Jump<T> withExtras(Bundle extras);

    Jump<T> withArgs(T args);

    Jump<T> withActivityOptions(ActivityOptionsCompat activityOptions);

    Jump<T> withConfig(Config config);

    void jump();

    void jumpForResult(int requestCode);

}