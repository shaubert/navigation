package com.shaubert.ui.jumper;

import android.content.Intent;
import android.os.Bundle;
import androidx.core.app.ActivityOptionsCompat;

public interface Jump<ARGS extends Args, CONFIG extends Config> {

    Intent getIntent();

    Jump<ARGS, CONFIG> withExtras(Bundle extras);

    Jump<ARGS, CONFIG> withArgs(ARGS args);

    Jump<ARGS, CONFIG> withActivityOptions(ActivityOptionsCompat activityOptions);

    Jump<ARGS, CONFIG> withConfig(CONFIG config);

    void jump();

    void jumpForResult(int requestCode);

}