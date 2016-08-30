package com.walid.autolayout.attr;

import android.view.View;

/**
 * Author   : walid
 * Data     : 2016-08-30  16:24
 * Describe :
 */
public class PaddingAttr extends AutoAttr {

    public PaddingAttr(int pxVal) {
        super(pxVal);
    }

    @Override
    protected int attrVal() {
        return Attrs.PADDING;
    }

    @Override
    protected void execute(View view, int val) {
        view.setPadding(val, val, val, val);
    }

}
