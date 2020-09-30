package com.shaubert.ui.jumper;

import android.content.Context;

public interface JumperFactory<JUMPER extends Jumper> {

    public JUMPER createFor(Context context);

    public JUMPER createFor(androidx.fragment.app.Fragment fragment);

}
