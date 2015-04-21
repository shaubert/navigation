package com.shaubert.ui.jumper;

import android.content.Intent;

public class SingleTopJump<T extends Args> extends StartNewActivityJump<T> {

    public SingleTopJump(Starter starter, Class<?> actClass) {
        super(starter, actClass);
    }

    @Override
    protected void setupIntent(Intent intent) {
        super.setupIntent(intent);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
    }
}
