package com.walid.autolayout.attr;

import android.view.View;

import java.lang.reflect.Method;

/**
 * Author   : walid
 * Data     : 2016-08-30  16:22
 * Describe :
 */
public class MaxHeightAttr extends AutoAttr {

    public MaxHeightAttr(int pxVal) {
        super(pxVal);
    }

    @Override
    protected int attrVal() {
        return Attrs.MAX_HEIGHT;
    }

    @Override
    protected void execute(View view, int val) {
        try {
            Method setMaxWidthMethod = view.getClass().getMethod("setMaxHeight", int.class);
            setMaxWidthMethod.invoke(view, val);
        } catch (Exception ignore) {
        }
    }

    public static MaxHeightAttr generate(int val) {
        return new MaxHeightAttr(val);
    }

    public static int getMaxHeight(View view) {
        try {
            Method setMaxWidthMethod = view.getClass().getMethod("getMaxHeight");
            return (int) setMaxWidthMethod.invoke(view);
        } catch (Exception ignore) {
        }
        return 0;
    }

}
