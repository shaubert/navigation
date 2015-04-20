package com.shaubert.navigation;

import android.content.Intent;

public interface NavigationController {

    void clearTop(Intent rootIntent, boolean finishCurrent);

    NavigationMethod<Bundler> getMethodByClass(Class<?> actClass);

}