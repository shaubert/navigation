package com.shaubert.ui.jumper;

import android.app.Activity;
import android.content.Intent;

public class CloseAppJump<T extends Args> extends ClearTopJump<T> {
    public CloseAppJump(Starter starter, Class<? extends Activity> baseActivityClass) {
        super(starter, baseActivityClass);
    }

    @Override
    protected void setupIntent(Intent intent) {
        super.setupIntent(intent);
        Extras.setupCloseAppIntent(intent);
    }
}
