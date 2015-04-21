package com.shaubert.ui.jumper;

import android.content.Intent;

public class StartNewActivityJump<T extends Args> extends AbstractJump<T> {

    public StartNewActivityJump(Starter starter, Class<?> actClass) {
        super(starter, actClass);
    }

    @Override
    protected void setupIntent(Intent intent) {
    }

}