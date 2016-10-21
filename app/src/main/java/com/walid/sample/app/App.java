package com.walid.sample.app;

import android.app.Application;

import com.walid.autolayout.config.AutoLayoutConifg;

/**
 * Author   : walid
 * Data     : 2016-08-30  19:13
 * Describe :
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AutoLayoutConifg.getInstance().initConfig(this, 640, 1136);
    }
}
