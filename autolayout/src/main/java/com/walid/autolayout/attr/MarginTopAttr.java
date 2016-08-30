package com.walid.autolayout.attr;

import android.view.View;
import android.view.ViewGroup;

/**
 * Author   : walid
 * Data     : 2016-08-30  16:22
 * Describe :
 */
public class MarginTopAttr extends AutoAttr {

    public MarginTopAttr(int pxVal) {
        super(pxVal, BaseParams.HEIGHT);
    }

    @Override
    protected int attrVal() {
        return Attrs.MARGIN_TOP;
    }

    @Override
    protected void execute(View view, int val) {
        if (!(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            return;
        }
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        lp.topMargin = val;
    }

    public static MarginTopAttr generate(int val) {
        return new MarginTopAttr(val);
    }

}
