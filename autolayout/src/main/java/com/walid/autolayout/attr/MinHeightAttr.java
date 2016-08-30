package com.walid.autolayout.attr;

import android.os.Build;
import android.view.View;

import java.lang.reflect.Field;

/**
 * Author   : walid
 * Data     : 2016-08-30  16:24
 * Describe :
 */
public class MinHeightAttr extends AutoAttr {

    public MinHeightAttr(int pxVal) {
        super(pxVal);
    }

    @Override
    protected int attrVal() {
        return Attrs.MIN_HEIGHT;
    }

    @Override
    protected void execute(View view, int val) {
        try {
            view.setMinimumHeight(val);
        } catch (Exception ignore) {
        }
    }

    public static MinHeightAttr generate(int val) {
        return  new MinHeightAttr(val);
    }

    public static int getMinHeight(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            return view.getMinimumHeight();
        } else {
            try {
                Field minHeight = view.getClass().getField("mMinHeight");
                minHeight.setAccessible(true);
                return (int) minHeight.get(view);
            } catch (Exception ignored) {
            }
        }
        return 0;
    }

}
