package com.walid.autolayout.attr;

import android.view.View;

import java.lang.reflect.Method;

/**
 * Author   : walid
 * Data     : 2016-08-30  16:24
 * Describe :
 */
public class MaxWidthAttr extends AutoAttr {

    public MaxWidthAttr(int pxVal) {
        super(pxVal);
    }

    @Override
    protected int attrVal() {
        return Attrs.MAX_WIDTH;
    }

    @Override
    protected void execute(View view, int val) {
        try {
            Method setMaxWidthMethod = view.getClass().getMethod("setMaxWidth", int.class);
            setMaxWidthMethod.invoke(view, val);
        } catch (Exception ignore) {
        }
    }

    public static MaxWidthAttr generate(int val) {
        return new MaxWidthAttr(val);
    }

    public static int getMaxWidth(View view) {
        try {
            Method setMaxWidthMethod = view.getClass().getMethod("getMaxWidth");
            return (int) setMaxWidthMethod.invoke(view);
        } catch (Exception ignore) {
        }
        return 0;
    }

}
