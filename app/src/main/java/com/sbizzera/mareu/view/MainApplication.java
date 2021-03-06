package com.sbizzera.mareu.view;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

/**
 * Creates by Boris SBIZZERA on 04/09/2019.
 */
public class MainApplication extends Application {

    private static Application sApplication;

    public static Application getApplication() {
        return sApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AndroidThreeTen.init(this);
        sApplication = this;
    }
}
