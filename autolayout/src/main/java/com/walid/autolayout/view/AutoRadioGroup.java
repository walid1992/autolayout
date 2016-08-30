package com.walid.autolayout.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;

import com.walid.autolayout.AutoLayoutInfo;
import com.walid.autolayout.utils.AutoLayoutUtils;

/**
 * Author   : walid
 * Data     : 2016-08-30  16:56
 * Describe :
 */

public class AutoRadioGroup extends RadioGroup {

    private final AutoLayoutUtils helper = new AutoLayoutUtils(this);

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
            helper.adjustChildren();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    class LayoutParams extends RadioGroup.LayoutParams implements AutoLayoutUtils.AutoLayoutParams {

        private AutoLayoutInfo autoLayoutInfo;

        LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            autoLayoutInfo = AutoLayoutUtils.getAutoLayoutInfo(c, attrs);
        }

        @Override
        public AutoLayoutInfo getAutoLayoutInfo() {
            return autoLayoutInfo;
        }

    }
}
