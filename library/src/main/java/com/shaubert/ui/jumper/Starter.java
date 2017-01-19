package com.shaubert.ui.jumper;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

public class Starter {
    private Jumper jumper;
    private Context context;
    private Fragment fragment;
    private android.support.v4.app.Fragment supportFragment;

    public Starter(Jumper jumper, Context context) {
        this.jumper = jumper;
        this.context = context;
    }

    public Starter(Jumper jumper, Fragment fragment) {
        this.jumper = jumper;
        this.fragment = fragment;
    }

    public Starter(Jumper jumper, android.support.v4.app.Fragment fragment) {
        this.jumper = jumper;
        this.supportFragment = fragment;
    }

    public Starter(Starter starter) {
        this.context = starter.context;
        this.fragment = starter.fragment;
        this.supportFragment = starter.supportFragment;
        this.jumper = starter.jumper;
    }

    @SuppressLint("NewApi")
    public void startActivity(Intent intent, Bundle bundle) {
        if (context != null) {
            if (bundle != null) {
                ActivityCompat.startActivity(context, intent, bundle);
            } else {
                context.startActivity(intent);
            }
        } else {
            if (bundle != null) {
                ActivityCompat.startActivity(getActivityFromFragment(), intent, bundle);
            } else {
                if (supportFragment != null) {
                    supportFragment.startActivity(intent);
                } else {
                    fragment.startActivity(intent);
                }
            }
        }
    }

    @SuppressLint("NewApi")
    private Activity getActivityFromFragment() {
        if (supportFragment != null) {
            return supportFragment.getActivity();
        } else {
            return fragment.getActivity();
        }
    }

    public Config getCurrentConfig() {
        Activity activity = null;
        if (context != null) {
            if (context instanceof Activity) {
                activity = (Activity) context;
            }
        } else {
            activity = getActivityFromFragment();
        }

        if (activity != null) {
            return jumper.getConfig(activity.getIntent());
        }

        return null;
    }

    @SuppressLint("NewApi")
    public void startActivityForResult(Intent intent, int requestCode, Bundle bundle) {
        if (context != null) {
            ActivityCompat.startActivityForResult((Activity) context, intent, requestCode, bundle);
        } else {
            if (bundle != null) {
                if (requestCode != 0 && requestCode != -1) {
                    throw new IllegalStateException("Fragment does not supported start activity for result with extra activity bundle");
                }
                ActivityCompat.startActivityForResult(getActivityFromFragment(), intent, requestCode, bundle);
            } else {
                if (supportFragment != null) {
                    supportFragment.startActivityForResult(intent, requestCode);
                } else {
                    fragment.startActivityForResult(intent, requestCode);
                }
            }
        }
    }

    public Context getContext() {
        return context == null ? getActivityFromFragment() : context;
    }

    public void finishActivity() {
        if (context != null && context instanceof Activity) {
            finishActivity((Activity) context);
        } else {
            Activity activity = getActivityFromFragment();
            if (activity != null) {
                finishActivity(activity);
            }
        }
    }

    protected void finishActivity(Activity activity) {
        activity.finish();
    }
}