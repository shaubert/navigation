package com.shaubert.ui.jumper;

import android.content.Intent;

public class StartNewActivityJump<ARGS extends Args, CONFIG extends Config> extends AbstractJump<ARGS, CONFIG> {

    public StartNewActivityJump(Starter starter, Class<?> actClass) {
        super(starter, actClass);
    }

    @Override
    protected void setupIntent(Intent intent) {
    }

}