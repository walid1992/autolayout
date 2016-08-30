package com.walid.autolayout.attr;

import android.view.View;
import android.view.ViewGroup;

/**
 * Author   : walid
 * Data     : 2016-08-30  16:22
 * Describe :
 */
public class MarginAttr extends AutoAttr {

    public MarginAttr(int pxVal) {
        super(pxVal);
    }

    @Override
    protected int attrVal() {
        return Attrs.MARGIN;
    }

    @Override
    protected void execute(View view, int val) {
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        lp.leftMargin = lp.rightMargin = lp.topMargin = lp.bottomMargin = val;
    }

}
