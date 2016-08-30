package com.walid.autolayout.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.walid.autolayout.AutoLayoutInfo;
import com.walid.autolayout.utils.AutoLayoutUtils;

/**
 * Author   : walid
 * Data     : 2016-08-30  16:20
 * Describe :
 */
public class AutoRecycleView extends RecyclerView {

    private final AutoLayoutUtils helper = new AutoLayoutUtils(this);

    public AutoRecycleView(Context context) {
        super(context);
    }

    public AutoRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoRecycleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    protected class LayoutParams extends RecyclerView.LayoutParams implements AutoLayoutUtils.AutoLayoutParams {

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
