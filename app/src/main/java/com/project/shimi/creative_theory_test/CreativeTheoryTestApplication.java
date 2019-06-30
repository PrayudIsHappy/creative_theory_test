package com.project.shimi.creative_theory_test;

import android.app.Application;
import android.content.Context;

import com.orhanobut.hawk.Hawk;

public class CreativeTheoryTestApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        CreativeTheoryTestApplication.context = getApplicationContext();
        Hawk.init(getApplicationContext()).build();
    }

    public static Context getAppContext() {
        return CreativeTheoryTestApplication.context;
    }

}

