package com.shaubert.ui.jumper;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

/**
 * Extend your class from {@link Args Bundler} to convert your parcelable fields into
 * {@link android.os.Bundle Bundle} and vice versa.
 */
public abstract class Args implements Parcelable {

    public static final String ARGS_KEY = "__args_key";

    public static <T extends Args> T fromArgs(Intent intent) {
        if (intent == null) return null;
        return fromArgs(intent.getExtras());
    }

    public static <T extends Args> T fromArgs(Bundle bundle) {
        if (bundle == null) return null;
        return bundle.getParcelable(ARGS_KEY);
    }

    public Bundle toBundle() {
        return toBundle(null);
    }

    public Bundle toBundle(Bundle out) {
        if (out == null) {
            out = new Bundle();
        }

        out.putParcelable(ARGS_KEY, this);

        return out;
    }

}