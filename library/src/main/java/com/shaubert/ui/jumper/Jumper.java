package com.shaubert.ui.jumper;

import android.content.Intent;
import com.shaubert.lifecycle.objects.LifecycleDispatcher;

public interface Jumper extends LifecycleDispatcher {

    <T extends Args> Jump<T> to(Class<?> actClass);

    <T extends Args> T getArgs(Intent intent);

    <T extends Config> T getConfig(Intent intent);

    void handleIntent(Intent intent);

}