package com.walid.autolayout.attr;

import android.view.View;

/**
 * Author   : walid
 * Data     : 2016-08-30  16:24
 * Describe :
 */
public class PaddingLeftAttr extends AutoAttr {

    public PaddingLeftAttr(int pxVal) {
        super(pxVal);
    }

    @Override
    protected int attrVal() {
        return Attrs.PADDING_LEFT;
    }

    @Override
    protected void execute(View view, int val) {
        int l = val;
        int t = view.getPaddingTop();
        int r = view.getPaddingRight();
        int b = view.getPaddingBottom();
        view.setPadding(l, t, r, b);
    }

    public static PaddingLeftAttr generate(int val) {
        return new PaddingLeftAttr(val);
    }

}
