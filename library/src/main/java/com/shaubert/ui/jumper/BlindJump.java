package com.shaubert.ui.jumper;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.ActivityOptionsCompat;

public interface BlindJump<ARGS extends Args, CONFIG extends Config> {

    BlindJump<ARGS, CONFIG> withExtras(Bundle extras);

    BlindJump<ARGS, CONFIG> withArgs(ARGS args);

    BlindJump<ARGS, CONFIG> withActivityOptions(ActivityOptionsCompat activityOptions);

    BlindJump<ARGS, CONFIG> withConfig(CONFIG config);

    void jump();

    void jumpForResult(int requestCode);

}