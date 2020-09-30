package com.shaubert.ui.jumper;

import android.content.Intent;

public class SingleTopJump<ARGS extends Args, CONFIG extends Config> extends StartNewActivityJump<ARGS, CONFIG> {

    public SingleTopJump(Starter starter, Class<?> actClass) {
        super(starter, actClass);
    }

    @Override
    protected void setupIntent(Intent intent) {
        super.setupIntent(intent);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
    }
}
