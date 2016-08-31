package com.walid.autolayout.config;

import android.content.Context;
import android.util.Log;

import com.walid.autolayout.bean.ScreenSize;
import com.walid.autolayout.utils.ScreenUtils;

/**
 * Author   : walid
 * Data     : 2016-08-30  16:21
 * Describe :
 */
public class AutoLayoutConifg {

    private int designWidth;
    private int designHeight;

    private int screenWidth;
    private int screenHeight;

    private AutoLayoutConifg() {
    }

    private static class SingletonHolder {
        static AutoLayoutConifg instance = new AutoLayoutConifg();
    }

    public static AutoLayoutConifg getInstance() {
        return SingletonHolder.instance;
    }

    public void initConfig(Context context, int width, int height) {
        initConfig(context, width, height, true);
    }

    public void initConfig(Context context, int width, int height, boolean userReal) {
        this.designWidth = width;
        this.designHeight = height;
        ScreenSize size = ScreenUtils.getScreenSize(context, userReal);
        this.screenWidth = size.getWidth();
        this.screenHeight = size.getHeight();
        Log.d("AutoLayoutConifg", " screenWidth =" + screenWidth + " ,screenHeight = " + screenHeight);
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getDesignHeight() {
        return designHeight;
    }

    public int getDesignWidth() {
        return designWidth;
    }

    public void checkParams() {
        if (designHeight <= 0 || designWidth <= 0) {
            throw new RuntimeException("you must set designWidth and designHeight  in your manifest file.");
        }
    }

}
