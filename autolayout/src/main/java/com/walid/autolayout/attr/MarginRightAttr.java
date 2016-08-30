package com.walid.autolayout.attr;

import android.view.View;
import android.view.ViewGroup;

/**
 * Author   : walid
 * Data     : 2016-08-30  16:22
 * Describe :
 */
public class MarginRightAttr extends AutoAttr {

    public MarginRightAttr(int pxVal) {
        super(pxVal);
    }

    @Override
    protected int attrVal() {
        return Attrs.MARGIN_RIGHT;
    }

    @Override
    protected void execute(View view, int val) {
        if (!(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            return;
        }
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        lp.rightMargin = val;
    }

    public static MarginRightAttr generate(int val) {
        return new MarginRightAttr(val);
    }

}
