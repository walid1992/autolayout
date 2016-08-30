package com.walid.autolayout.attr;

import android.view.View;
import android.view.ViewGroup;

/**
 * Author   : walid
 * Data     : 2016-08-30  16:22
 * Describe :
 */
public class MarginBottomAttr extends AutoAttr {

    public MarginBottomAttr(int pxVal) {
        super(pxVal, BaseParams.HEIGHT);
    }

    @Override
    protected int attrVal() {
        return Attrs.MARGIN_BOTTOM;
    }

    @Override
    protected void execute(View view, int val) {
        if (!(view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            return;
        }
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        lp.bottomMargin = val;
    }

    public static MarginBottomAttr generate(int val) {
        return new MarginBottomAttr(val);
    }

}
