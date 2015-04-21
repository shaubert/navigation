package com.shaubert.ui.jumper;

import android.content.Intent;

public class ClearTopJump<T extends Args> extends SingleTopJump<T> {

    public ClearTopJump(Starter starter, Class<?> actClass) {
        super(starter, actClass);
    }

    @Override
    protected void setupIntent(Intent intent) {
        super.setupIntent(intent);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }
}
