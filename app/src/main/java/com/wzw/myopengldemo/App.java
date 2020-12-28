package com.wzw.myopengldemo;

import android.app.Application;

import com.wzw.myopengldemo.one.utils.AppCore;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppCore.getInstance().init(this);
    }
}
