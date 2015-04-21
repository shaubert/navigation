package com.shaubert.ui.jumper;

import android.app.Activity;
import android.content.Intent;

public class CloseAllAndOpenIntentJump<T extends Args> extends ClearTopJump<T> {
    private Intent openIntent;

    public CloseAllAndOpenIntentJump(Starter starter, Class<? extends Activity> baseActivityClass, Intent openIntent) {
        super(starter, baseActivityClass);
        this.openIntent = openIntent;
    }

    @Override
    protected void setupIntent(Intent intent) {
        super.setupIntent(intent);
        Extras.setupCloseAllAndOpenActivityIntent(intent, openIntent);
    }
}
