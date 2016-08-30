package com.walid.autolayout.utils;

import android.util.TypedValue;

/**
 * Created by walid on 16/8/12.
 */
public class DimenUtils {

    private static int getComplexUnit(int data) {
        return TypedValue.COMPLEX_UNIT_MASK & (data);
    }

    public static boolean isPxVal(TypedValue val) {
        return val != null && val.type == TypedValue.TYPE_DIMENSION && getComplexUnit(val.data) == TypedValue.COMPLEX_UNIT_PX;
    }

}
