package com.walid.autolayout.attr;

import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

/**
 * Author   : walid
 * Data     : 2016-08-30  16:25
 * Describe :
 */
public class TextSizeAttr extends AutoAttr {

    public TextSizeAttr(int pxVal) {
        super(pxVal);
    }

    @Override
    protected int attrVal() {
        return Attrs.TEXTSIZE;
    }

    @Override
    protected void execute(View view, int val) {
        if (!(view instanceof TextView))
            return;
        ((TextView) view).setIncludeFontPadding(false);
        ((TextView) view).setTextSize(TypedValue.COMPLEX_UNIT_PX, val);
    }

    public static TextSizeAttr generate(int val) {
        return new TextSizeAttr(val);
    }

}
