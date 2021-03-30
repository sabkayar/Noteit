package com.example.recyclerview;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class DataBaseCheck extends Application {

    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }
}
