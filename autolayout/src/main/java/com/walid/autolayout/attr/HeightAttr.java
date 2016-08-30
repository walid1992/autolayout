package com.walid.autolayout.attr;

import android.view.View;
import android.view.ViewGroup;

/**
 * Author   : walid
 * Data     : 2016-08-30  16:22
 * Describe :
 */
public class HeightAttr extends AutoAttr {

    public HeightAttr(int pxVal) {
        super(pxVal);
    }

    @Override
    protected int attrVal() {
        return Attrs.HEIGHT;
    }

    @Override
    protected void execute(View view, int val) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = val;
    }

    public static HeightAttr generate(int val) {
        return new HeightAttr(val);
    }

}
