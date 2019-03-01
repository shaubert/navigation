package com.shaubert.ui.jumper;

import android.app.Fragment;
import android.content.Context;

public interface JumperFactory<JUMPER extends Jumper> {

    public JUMPER createFor(Context context);

    public JUMPER createFor(Fragment fragment);

    public JUMPER createFor(androidx.fragment.app.Fragment fragment);

}
