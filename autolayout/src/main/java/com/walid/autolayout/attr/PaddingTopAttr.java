package com.walid.autolayout.attr;

import android.view.View;

/**
 * Author   : walid
 * Data     : 2016-08-30  16:25
 * Describe :
 */
public class PaddingTopAttr extends AutoAttr {

    public PaddingTopAttr(int pxVal) {
        super(pxVal);
    }

    @Override
    protected int attrVal() {
        return Attrs.PADDING_TOP;
    }

    @Override
    protected void execute(View view, int val) {
        int l = view.getPaddingLeft();
        int t = val;
        int r = view.getPaddingRight();
        int b = view.getPaddingBottom();
        view.setPadding(l, t, r, b);
    }

    public static PaddingTopAttr generate(int val) {
        return new PaddingTopAttr(val);
    }

}
