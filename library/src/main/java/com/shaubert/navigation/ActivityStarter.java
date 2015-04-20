package com.shaubert.navigation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

public class ActivityStarter {
    private Context context;
    private Fragment fragment;
    private android.support.v4.app.Fragment supportFragment;

    private int enterAnim = -1;
    private int exitAnim = -1;

    public ActivityStarter(Context context) {
        this.context = context;
    }

    public ActivityStarter(Fragment fragment) {
        this.fragment = fragment;
    }

    public ActivityStarter(android.support.v4.app.Fragment fragment) {
        this.supportFragment = fragment;
    }

    public ActivityStarter(ActivityStarter starter) {
        this.fragment = starter.fragment;
        this.context = starter.context;
        this.enterAnim = starter.enterAnim;
        this.exitAnim = starter.exitAnim;
    }

    @SuppressLint("NewApi")
    public void startActivity(Intent intent, Bundle bundle) {
        if (context != null) {
            if (bundle != null) {
                ActivityCompat.startActivity((Activity) context, intent, bundle);
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
        setupAnimations();
    }

    @SuppressLint("NewApi")
    private Activity getActivityFromFragment() {
        if (supportFragment != null) {
            return supportFragment.getActivity();
        } else {
            return fragment.getActivity();
        }
    }

    public NavigationController getNavigationController() {
        if (fragment instanceof NavigationControllerHolder) {
            return  ((NavigationControllerHolder) fragment).getNavigationController();
        } else if (context instanceof NavigationControllerHolder) {
            return ((NavigationControllerHolder) context).getNavigationController();
        }
        return null;
    }

    public void overridePendingTransition(int enterAnim, int exitAnim) {
        this.enterAnim = enterAnim;
        this.exitAnim = exitAnim;
    }

    public void restorePendingTransition() {
        enterAnim = -1;
        exitAnim = -1;
    }

    private void setupAnimations() {
        if (enterAnim != -1 || exitAnim != -1) {
            Activity activity;
            if (context != null && context instanceof Activity) {
                activity = (Activity) context;
            } else {
                activity = getActivityFromFragment();
            }
            if (activity != null) {
                activity.overridePendingTransition(enterAnim, exitAnim);
            }
        }
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
        setupAnimations();
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

    private void finishActivity(Activity activity) {
        activity.finish();
    }
}