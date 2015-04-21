package com.shaubert.ui.jumper;

import android.app.Fragment;
import android.content.Context;

public abstract class AbstractJumper implements Jumper {

    public static final String TAG = AbstractJumper.class.getSimpleName();

    private Starter starter;

    public AbstractJumper(Context context) {
        this.starter = new Starter(context);
    }

    public AbstractJumper(Fragment fragment) {
        this.starter = new Starter(fragment);
    }

    public AbstractJumper(android.support.v4.app.Fragment fragment) {
        this.starter = new Starter(fragment);
    }

    public Starter getStarter() {
        return starter;
    }

}