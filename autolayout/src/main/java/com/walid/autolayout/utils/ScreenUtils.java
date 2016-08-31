package com.walid.autolayout.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.walid.autolayout.bean.ScreenSize;
import com.walid.autolayout.config.AutoLayoutConifg;

/**
 * Author   : walid
 * Data     : 2016-08-31  11:33
 * Describe :
 */
public class ScreenUtils {

    private static final String TAG = "ScreenUtils";

    public static float getRealPxByWidth(float pxValue) {
//        if (Float.isNaN(pxValue)) {
//            return pxValue;
//        }
        AutoLayoutConifg conifg = AutoLayoutConifg.getInstance();
        float realPx = (pxValue * conifg.getScreenWidth() / conifg.getDesignWidth());
        return realPx > 0.005 && realPx < 1 ? 1 : (float) Math.rint(realPx);
    }

    public static float getRealPxByHeight(float pxValue) {
//        if (Float.isNaN(pxValue)) {
//            return pxValue;
//        }
        AutoLayoutConifg conifg = AutoLayoutConifg.getInstance();
        float realPx = (pxValue * conifg.getScreenHeight() / conifg.getDesignHeight());
        return realPx > 0.005 && realPx < 1 ? 1 : (float) Math.rint(realPx);
    }

    public static int getStatusBarHeight(Context context) {
        int result = 0;
        try {
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                result = context.getResources().getDimensionPixelSize(resourceId);
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ScreenSize getScreenSize(Context context, boolean userReal) {
        ScreenSize screenSize = new ScreenSize();
        WindowManager w = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);
        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;

        if (userReal) {
            screenSize.setWidth(widthPixels);
            screenSize.setHeight(heightPixels - getStatusBarHeight(context));
            return screenSize;
        }

        // includes window decorations (statusbar bar/menu bar)
        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17) {
            try {
                widthPixels = (Integer) Display.class.getMethod("getRawWidth").invoke(d);
                heightPixels = (Integer) Display.class.getMethod("getRawHeight").invoke(d);
            } catch (Exception ignored) {
            }
        }
        // includes window decorations (statusbar bar/menu bar)
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                Point realSize = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(d, realSize);
                widthPixels = realSize.x;
                heightPixels = realSize.y;
            } catch (Exception ignored) {
            }
        }
        screenSize.setWidth(widthPixels);
        screenSize.setHeight(heightPixels);
        return screenSize;
    }

}
