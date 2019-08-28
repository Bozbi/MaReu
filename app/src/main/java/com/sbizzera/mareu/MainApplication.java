package com.sbizzera.mareu;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;

/**
 * Creates by Boris SBIZZERA on 28/08/2019.
 */
public class MainApplication extends Application {
    private static Application sApplication;

    @Override public void onCreate() {
        super.onCreate();
        sApplication = this;
        AndroidThreeTen.init(this);
    }

    public static Application getInstance(){
        return sApplication;
    }

}
