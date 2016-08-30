package com.walid.autolayout.attr;

import android.os.Build;
import android.view.View;

import java.lang.reflect.Field;

/**
 * Author   : walid
 * Data     : 2016-08-30  16:24
 * Describe :
 */
public class MinWidthAttr extends AutoAttr {

    public MinWidthAttr(int pxVal) {
        super(pxVal);
    }

    @Override
    protected int attrVal() {
        return Attrs.MIN_WIDTH;
    }

    @Override
    protected void execute(View view, int val) {
        view.setMinimumWidth(val);
    }

    public static int getMinWidth(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            return view.getMinimumWidth();
        try {
            Field minWidth = view.getClass().getField("mMinWidth");
            minWidth.setAccessible(true);
            return (int) minWidth.get(view);
        } catch (Exception ignore) {
        }
        return 0;
    }

    public static MinWidthAttr generate(int val) {
        return new MinWidthAttr(val);
    }

}
