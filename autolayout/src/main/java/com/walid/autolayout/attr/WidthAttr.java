package com.walid.autolayout.attr;

import android.view.View;
import android.view.ViewGroup;

/**
 * Author   : walid
 * Data     : 2016-08-30  16:25
 * Describe :
 */
public class WidthAttr extends AutoAttr {

    public WidthAttr(int pxVal) {
        super(pxVal);
    }

    @Override
    protected int attrVal() {
        return Attrs.WIDTH;
    }

    @Override
    protected void execute(View view, int val) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.width = val;
    }

    public static WidthAttr generate(int val) {
        return new WidthAttr(val);
    }

}
