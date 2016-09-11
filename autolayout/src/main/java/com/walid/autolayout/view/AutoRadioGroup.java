package com.walid.autolayout.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;

import com.walid.autolayout.utils.AutoLayoutInfo;
import com.walid.autolayout.utils.AutoUtils;

/**
 * Author   : walid
 * Data     : 2016-08-30  16:56
 * Describe :
 */

public class AutoRadioGroup extends RadioGroup {

    public AutoRadioGroup(Context context) {
        super(context);
    }

    public AutoRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!isInEditMode()) {
            AutoUtils.autoLayoutAdjustChildren(this);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    class LayoutParams extends RadioGroup.LayoutParams implements AutoUtils.AutoLayoutParams {

        private AutoLayoutInfo autoLayoutInfo;

        LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            autoLayoutInfo = AutoUtils.getAutoLayoutInfo(c, attrs);
        }

        @Override
        public AutoLayoutInfo getAutoLayoutInfo() {
            return autoLayoutInfo;
        }

    }
}
