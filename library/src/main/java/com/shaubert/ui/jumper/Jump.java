package com.shaubert.ui.jumper;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.ActivityOptionsCompat;

public interface Jump<ARGS extends Args, CONFIG extends Config> extends BlindJump<ARGS, CONFIG> {

    @Override
    Jump<ARGS, CONFIG> withExtras(Bundle extras);

    @Override
    Jump<ARGS, CONFIG> withArgs(ARGS args);

    @Override
    Jump<ARGS, CONFIG> withActivityOptions(ActivityOptionsCompat activityOptions);

    @Override
    Jump<ARGS, CONFIG> withConfig(CONFIG config);

    Intent getIntent();

}