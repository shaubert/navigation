package com.shaubert.ui.jumper;

import android.content.Intent;
import com.shaubert.lifecycle.objects.LifecycleDispatcher;

public interface Jumper extends LifecycleDispatcher, ConfigResolver {

    <ARGS extends Args, CONFIG extends Config> Jump<ARGS, CONFIG> to(Class<?> actClass);

    <T extends Args> T getArgs(Intent intent);

    void handleIntent(Intent intent);

}