package com.walid.autolayout.attr;

import android.view.View;
import android.view.ViewGroup;

/**
 * Author   : walid
 * Data     : 2016-08-30  16:22
 * Describe :
 */
public class MarginLeftAttr extends AutoAttr {

    public MarginLeftAttr(int pxVal) {
        super(pxVal);
    }

    @Override
    protected int attrVal() {
        return Attrs.MARGIN_LEFT;
    }

    @Override
    protected void execute(View view, int val) {
        if (!(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            return;
        }
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        lp.leftMargin = val;
    }

    public static MarginLeftAttr generate(int val) {
        return new MarginLeftAttr(val);
    }

}
