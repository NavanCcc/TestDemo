package com.example.chenren1.hello;

import android.app.Application;
import android.content.Context;

public class DemoApplication extends Application {

    private static Application sApplication = null;//Global Application


    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }

    public static Context getAppContext() {
        return sApplication.getApplicationContext();
    }
}
