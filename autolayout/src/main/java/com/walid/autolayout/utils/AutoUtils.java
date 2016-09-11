package com.walid.autolayout.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.StringDef;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.walid.autolayout.R;
import com.walid.autolayout.attr.Attrs;
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
import com.walid.autolayout.view.AutoRelativeLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Author   : walid
 * Data     : 2016-08-30  16:21
 * Describe :
 */
public class AutoUtils {


    @StringDef({AutoViewType.LINEARLAYOUT, AutoViewType.FRAMELAYOUT, AutoViewType.RELATIVELAYOUT, AutoViewType.RADIOGROUP, AutoViewType.SCROLLVIEW})
    @Retention(RetentionPolicy.SOURCE)
    public @interface AutoViewType {
        String LINEARLAYOUT = "LinearLayout";
        String FRAMELAYOUT = "FrameLayout";
        String RELATIVELAYOUT = "RelativeLayout";
        String RADIOGROUP = "RadioGroup";
        String SCROLLVIEW = "ScrollView";
    }

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

    public static void autoLayoutAdjustChildren(View view) {
        AutoLayoutConifg.getInstance().checkParams();
        if (view == null) {
            return;
        }
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params instanceof AutoLayoutParams) {
            AutoLayoutInfo info = ((AutoLayoutParams) params).getAutoLayoutInfo();
            if (info != null) {
                info.fillAttrs(view);
            }
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                autoLayoutAdjustChildren(viewGroup.getChildAt(i));
            }
        }
    }

    public static void autoInitParams(View view) {
        if (autoed(view)) {
            return;
        }
        autoSize(view);
        autoPadding(view);
        autoMargin(view);
        autoTextSize(view);
    }

    public static void autoInitParams(View view, int attrs) {
        AutoLayoutInfo autoLayoutInfo = AutoLayoutInfo.getAttrFromView(view, attrs);
        if (autoLayoutInfo != null) {
            autoLayoutInfo.fillAttrs(view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                autoInitParams(viewGroup.getChildAt(i));
            }
        }
    }

    public static void autoTextSize(View view) {
        autoInitParams(view, Attrs.TEXTSIZE);
    }

    public static void autoMargin(View view) {
        autoInitParams(view, Attrs.MARGIN);
    }

    public static void autoPadding(View view) {
        autoInitParams(view, Attrs.PADDING);
    }

    public static void autoSize(View view) {
        autoInitParams(view, Attrs.WIDTH | Attrs.HEIGHT);
    }

    //  判断是否auto
    public static boolean autoed(View view) {
        Object tag = view.getTag(R.id.id_tag_autolayout_size);
        if (tag != null) {
            return true;
        }
        view.setTag(R.id.id_tag_autolayout_size, "Just Identify");
        return false;
    }

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
        return info;
    }

    public static View genAutoView(String name, Context context, AttributeSet attrs) {
        View view = null;
        switch (name) {
            case AutoViewType.FRAMELAYOUT:
                view = new AutoFrameLayout(context, attrs);
                break;
            case AutoViewType.LINEARLAYOUT:
                view = new AutoLinearLayout(context, attrs);
                break;
            case AutoViewType.RELATIVELAYOUT:
                view = new AutoRelativeLayout(context, attrs);
                break;
            case AutoViewType.RADIOGROUP:
                view = new AutoRadioGroup(context, attrs);
                break;
//            case AutoViewType.SCROLLVIEW:
//                view = new AutoScrollView(context, attrs);
//                break;
        }
        return view;
    }

    public interface AutoLayoutParams {
        AutoLayoutInfo getAutoLayoutInfo();
    }

}
