package com.shaubert.navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class NavExtras {

    public static final String OPEN_INTENT_EXTRA = "open_intent_extra";
    public static final String CLEAN_UP_TASK_AND_FINISH_EXTRA = "clean_up_task_and_finish_extra";
    public static final String UP_AS_FINISH_NAVIGATION_EXTRA = "up_as_finish_navigation";
    public static final String SUPPRESS_UP_NAVIGATION_EXTRA = "suppress_up_navigation";
    public static final String BACKWARD_TRANSITION_EXTRA = "backward_transition_extra";

    public static void setupCloseAppIntent(Intent intent) {
        intent.putExtra(CLEAN_UP_TASK_AND_FINISH_EXTRA, true);
    }

    public static void setupCloseAllAndOpenActivityIntent(Intent intent, Intent openIntent) {
        intent.putExtra(OPEN_INTENT_EXTRA, openIntent);
        intent.putExtra(CLEAN_UP_TASK_AND_FINISH_EXTRA, true);
    }

    public static Bundle setUpAsFinish() {
        return setUpAsFinish(null);
    }

    public static Bundle setUpAsFinish(Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putBoolean(UP_AS_FINISH_NAVIGATION_EXTRA, true);
        return bundle;
    }

    public static Bundle suppressUpNavigation() {
        return suppressUpNavigation(null);
    }

    public static Bundle suppressUpNavigation(Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putBoolean(SUPPRESS_UP_NAVIGATION_EXTRA, true);
        return bundle;
    }

    public static Bundle setupBackwardTransition(Bundle bundle, TransitionsBundle transitionsBundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putParcelable(BACKWARD_TRANSITION_EXTRA, transitionsBundle);
        return bundle;
    }

    public static TransitionsBundle getBackwardTransition(Activity activity) {
        if (activity != null) {
            Intent intent = activity.getIntent();
            if (intent != null) {
                return intent.getParcelableExtra(BACKWARD_TRANSITION_EXTRA);
            }
        }
        return null;
    }

}