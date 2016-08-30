package com.walid.autolayout.attr;

import android.view.View;

/**
 * Author   : walid
 * Data     : 2016-08-30  16:24
 * Describe :
 */
public class PaddingBottomAttr extends AutoAttr {

    public PaddingBottomAttr(int pxVal) {
        super(pxVal);
    }

    @Override
    protected int attrVal() {
        return Attrs.PADDING_BOTTOM;
    }

    @Override
    protected void execute(View view, int val) {
        int l = view.getPaddingLeft();
        int t = view.getPaddingTop();
        int r = view.getPaddingRight();
        int b = val;
        view.setPadding(l, t, r, b);
    }

    public static PaddingBottomAttr generate(int val) {
        return new PaddingBottomAttr(val);
    }

}
