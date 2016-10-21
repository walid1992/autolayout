package com.walid.sample.app;

import android.app.Application;
import android.content.Context;

import com.walid.autolayout.config.AutoLayoutConifg;

/**
 * Author   : walid
 * Data     : 2016-08-30  19:13
 * Describe :
 */

public class App extends Application {

    public static Context instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        AutoLayoutConifg.getInstance().initConfig(this, 640, 1136);
    }

}
