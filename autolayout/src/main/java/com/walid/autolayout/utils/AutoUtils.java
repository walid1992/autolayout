package com.walid.autolayout.utils;

import android.view.View;

import com.walid.autolayout.AutoLayoutInfo;
import com.walid.autolayout.R;
import com.walid.autolayout.attr.Attrs;
import com.walid.autolayout.config.AutoLayoutConifg;

/**
 * Created by walid on 16/8/12.
 */
public class AutoUtils {

    public static void auto(View view) {
        autoSize(view);
        autoPadding(view);
        autoMargin(view);
        autoTextSize(view);
    }

    public static void auto(View view, int attrs) {
        AutoLayoutInfo autoLayoutInfo = AutoLayoutInfo.getAttrFromView(view, attrs);
        if (autoLayoutInfo != null) {
            autoLayoutInfo.fillAttrs(view);
        }
    }

    public static void autoTextSize(View view) {
        auto(view, Attrs.TEXTSIZE);
    }

    public static void autoMargin(View view) {
        auto(view, Attrs.MARGIN);
    }

    public static void autoPadding(View view) {
        auto(view, Attrs.PADDING);
    }

    public static void autoSize(View view) {
        auto(view, Attrs.WIDTH | Attrs.HEIGHT);
    }

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

    //  判断是否auto
    public static boolean autoed(View view) {
        Object tag = view.getTag(R.id.id_tag_autolayout_size);
        if (tag != null) {
            return true;
        }
        view.setTag(R.id.id_tag_autolayout_size, "Just Identify");
        return false;
    }

}
