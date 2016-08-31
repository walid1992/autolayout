package com.walid.autolayout.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.walid.autolayout.attr.HeightAttr;
import com.walid.autolayout.attr.MarginAttr;
import com.walid.autolayout.attr.MarginBottomAttr;
import com.walid.autolayout.attr.MarginLeftAttr;
import com.walid.autolayout.attr.MarginRightAttr;
import com.walid.autolayout.attr.MarginTopAttr;
import com.walid.autolayout.attr.MaxHeightAttr;
import com.walid.autolayout.attr.MaxWidthAttr;
import com.walid.autolayout.attr.MinHeightAttr;
import com.walid.autolayout.attr.MinWidthAttr;
import com.walid.autolayout.attr.PaddingAttr;
import com.walid.autolayout.attr.PaddingBottomAttr;
import com.walid.autolayout.attr.PaddingLeftAttr;
import com.walid.autolayout.attr.PaddingRightAttr;
import com.walid.autolayout.attr.PaddingTopAttr;
import com.walid.autolayout.attr.TextSizeAttr;
import com.walid.autolayout.attr.WidthAttr;
import com.walid.autolayout.config.AutoLayoutConifg;
import com.walid.autolayout.view.AutoFrameLayout;
import com.walid.autolayout.view.AutoLinearLayout;
import com.walid.autolayout.view.AutoRadioGroup;
import com.walid.autolayout.view.AutoRecycleView;
import com.walid.autolayout.view.AutoRelativeLayout;
import com.walid.autolayout.view.AutoScrollView;

/**
 * Author   : walid
 * Data     : 2016-08-30  16:21
 * Describe :
 */
public class AutoLayoutUtils {

    private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
    private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
    private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";
    private static final String LAYOUT_RECYCLERVIEW = "android.support.v7.widget.RecyclerView";
    private static final String LAYOUT_RADIOGROUP = "RadioGroup";
    private static final String LAYOUT_SCROLLVIEW = "ScrollView";

    private static final int[] LL = new int[]{
            android.R.attr.textSize,
            android.R.attr.padding,//
            android.R.attr.paddingLeft,//
            android.R.attr.paddingTop,//
            android.R.attr.paddingRight,//
            android.R.attr.paddingBottom,//
            android.R.attr.layout_width,//
            android.R.attr.layout_height,//
            android.R.attr.layout_margin,//
            android.R.attr.layout_marginLeft,//
            android.R.attr.layout_marginTop,//
            android.R.attr.layout_marginRight,//
            android.R.attr.layout_marginBottom,//
            android.R.attr.maxWidth,//
            android.R.attr.maxHeight,//
            android.R.attr.minWidth,//
            android.R.attr.minHeight,//16843072
    };

    private static final int INDEX_TEXT_SIZE = 0;
    private static final int INDEX_PADDING = 1;
    private static final int INDEX_PADDING_LEFT = 2;
    private static final int INDEX_PADDING_TOP = 3;
    private static final int INDEX_PADDING_RIGHT = 4;
    private static final int INDEX_PADDING_BOTTOM = 5;
    private static final int INDEX_WIDTH = 6;
    private static final int INDEX_HEIGHT = 7;
    private static final int INDEX_MARGIN = 8;
    private static final int INDEX_MARGIN_LEFT = 9;
    private static final int INDEX_MARGIN_TOP = 10;
    private static final int INDEX_MARGIN_RIGHT = 11;
    private static final int INDEX_MARGIN_BOTTOM = 12;
    private static final int INDEX_MAX_WIDTH = 13;
    private static final int INDEX_MAX_HEIGHT = 14;
    private static final int INDEX_MIN_WIDTH = 15;
    private static final int INDEX_MIN_HEIGHT = 16;

    public static void adjustChildren(View view) {
        AutoLayoutConifg.getInstance().checkParams();
        initParams(view);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                adjustChildren(viewGroup.getChildAt(i));
            }
        }
    }

    private static void initParams(View view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params instanceof AutoLayoutParams) {
            AutoLayoutInfo info = ((AutoLayoutParams) params).getAutoLayoutInfo();
            if (info != null) {
                info.fillAttrs(view);
            }
        }
    }

//    public static void adjustChildren(ViewGroup host) {
//        AutoLayoutConifg.getInstance().checkParams();
//        int count = host.getChildCount();
//        for (int i = 0; i < count; i++) {
//            View view = host.getChildAt(i);
//            ViewGroup.LayoutParams params = view.getLayoutParams();
//            if (params instanceof AutoLayoutParams) {
//                AutoLayoutInfo info = ((AutoLayoutParams) params).getAutoLayoutInfo();
//                if (info != null) {
//                    info.fillAttrs(view);
//                }
//            }
//        }
//    }

    public static AutoLayoutInfo getAutoLayoutInfo(Context context, AttributeSet attrs) {
        AutoLayoutInfo info = new AutoLayoutInfo();
        TypedArray array = context.obtainStyledAttributes(attrs, LL);
        int n = array.getIndexCount();
        for (int i = 0; i < n; i++) {
            int index = array.getIndex(i);
            if (!DimenUtils.isPxVal(array.peekValue(index))) {
                continue;
            }
            int pxVal;
            try {
                pxVal = array.getDimensionPixelOffset(index, 0);
            } catch (Exception ignore) {//not dimension
                continue;
            }
            switch (index) {
                case INDEX_TEXT_SIZE:
                    info.addAttr(new TextSizeAttr(pxVal));
                    break;
                case INDEX_PADDING:
                    info.addAttr(new PaddingAttr(pxVal));
                    break;
                case INDEX_PADDING_LEFT:
                    info.addAttr(new PaddingLeftAttr(pxVal));
                    break;
                case INDEX_PADDING_TOP:
                    info.addAttr(new PaddingTopAttr(pxVal));
                    break;
                case INDEX_PADDING_RIGHT:
                    info.addAttr(new PaddingRightAttr(pxVal));
                    break;
                case INDEX_PADDING_BOTTOM:
                    info.addAttr(new PaddingBottomAttr(pxVal));
                    break;
                case INDEX_WIDTH:
                    info.addAttr(new WidthAttr(pxVal));
                    break;
                case INDEX_HEIGHT:
                    info.addAttr(new HeightAttr(pxVal));
                    break;
                case INDEX_MARGIN:
                    info.addAttr(new MarginAttr(pxVal));
                    break;
                case INDEX_MARGIN_LEFT:
                    info.addAttr(new MarginLeftAttr(pxVal));
                    break;
                case INDEX_MARGIN_TOP:
                    info.addAttr(new MarginTopAttr(pxVal));
                    break;
                case INDEX_MARGIN_RIGHT:
                    info.addAttr(new MarginRightAttr(pxVal));
                    break;
                case INDEX_MARGIN_BOTTOM:
                    info.addAttr(new MarginBottomAttr(pxVal));
                    break;
                case INDEX_MAX_WIDTH:
                    info.addAttr(new MaxWidthAttr(pxVal));
                    break;
                case INDEX_MAX_HEIGHT:
                    info.addAttr(new MaxHeightAttr(pxVal));
                    break;
                case INDEX_MIN_WIDTH:
                    info.addAttr(new MinWidthAttr(pxVal));
                    break;
                case INDEX_MIN_HEIGHT:
                    info.addAttr(new MinHeightAttr(pxVal));
                    break;
            }
        }
        array.recycle();
        Log.d("AutoLayoutUtils", " getAutoLayoutInfo " + info.toString());
        return info;
    }

    public static View genAutoView(String name, Context context, AttributeSet attrs) {
        View view = null;
        switch (name) {
            case LAYOUT_FRAMELAYOUT:
                view = new AutoFrameLayout(context, attrs);
                break;
            case LAYOUT_LINEARLAYOUT:
                view = new AutoLinearLayout(context, attrs);
                break;
            case LAYOUT_RELATIVELAYOUT:
                view = new AutoRelativeLayout(context, attrs);
                break;
            case LAYOUT_RECYCLERVIEW:
                view = new AutoRecycleView(context, attrs);
                break;
            case LAYOUT_RADIOGROUP:
                view = new AutoRadioGroup(context, attrs);
                break;
            case LAYOUT_SCROLLVIEW:
                view = new AutoScrollView(context, attrs);
                break;
        }
        return view;
    }

    public interface AutoLayoutParams {
        AutoLayoutInfo getAutoLayoutInfo();
    }

}
