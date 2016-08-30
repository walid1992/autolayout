package com.walid.autolayout.attr;

import android.view.View;

/**
 * Author   : walid
 * Data     : 2016-08-30  16:25
 * Describe :
 */
public class PaddingRightAttr extends AutoAttr {

    public PaddingRightAttr(int pxVal) {
        super(pxVal);
    }

    @Override
    protected int attrVal() {
        return Attrs.PADDING_RIGHT;
    }

    @Override
    protected void execute(View view, int val) {
        int l = view.getPaddingLeft();
        int t = view.getPaddingTop();
        int r = val;
        int b = view.getPaddingBottom();
        view.setPadding(l, t, r, b);
    }

    public static PaddingRightAttr generate(int val) {
        return new PaddingRightAttr(val);
    }

}
