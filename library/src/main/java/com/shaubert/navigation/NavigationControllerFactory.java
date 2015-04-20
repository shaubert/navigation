package com.shaubert.navigation;

import android.app.Fragment;
import android.content.Context;

public interface NavigationControllerFactory {

    public NavigationController create(Context context);

    public NavigationController create(Fragment fragment);

    public NavigationController create(android.support.v4.app.Fragment fragment);

}
