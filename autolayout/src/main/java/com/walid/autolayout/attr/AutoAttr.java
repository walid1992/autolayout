package com.walid.autolayout.attr;

import android.support.annotation.IntDef;
import android.view.View;

import com.walid.autolayout.utils.AutoUtils;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Author   : walid
 * Data     : 2016-08-30  16:22
 * Describe :
 */
public abstract class AutoAttr {

    @IntDef({BaseParams.WIDTH, BaseParams.HEIGHT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface BaseParams {
        int WIDTH = 1;
        int HEIGHT = 2;
    }

    private int pxVal;
    @BaseParams
    private int baseParams = BaseParams.WIDTH;

    public AutoAttr(int pxVal) {
        this.pxVal = pxVal;
    }

    public AutoAttr(int pxVal, @BaseParams int baseParams) {
        this.pxVal = pxVal;
        this.baseParams = baseParams;
    }

    public void apply(View view) {
        int val;
        if (baseParams == BaseParams.WIDTH) {
            val = (int) AutoUtils.getRealPxByWidth(pxVal);
        } else {
            val = (int) AutoUtils.getRealPxByHeight(pxVal);
        }

        if (val > 0) {
            val = Math.max(val, 1);
        }
        execute(view, val);
    }

    protected abstract int attrVal();

    protected abstract void execute(View view, int val);

    @Override
    public String toString() {
        return "AutoAttr{" + "pxVal=" + pxVal + '}';
    }

}
