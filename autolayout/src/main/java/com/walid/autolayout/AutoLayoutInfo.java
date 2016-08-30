package com.walid.autolayout;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.walid.autolayout.attr.Attrs;
import com.walid.autolayout.attr.AutoAttr;
import com.walid.autolayout.attr.HeightAttr;
import com.walid.autolayout.attr.MarginBottomAttr;
import com.walid.autolayout.attr.MarginLeftAttr;
import com.walid.autolayout.attr.MarginRightAttr;
import com.walid.autolayout.attr.MarginTopAttr;
import com.walid.autolayout.attr.MaxHeightAttr;
import com.walid.autolayout.attr.MaxWidthAttr;
import com.walid.autolayout.attr.MinHeightAttr;
import com.walid.autolayout.attr.MinWidthAttr;
import com.walid.autolayout.attr.PaddingBottomAttr;
import com.walid.autolayout.attr.PaddingLeftAttr;
import com.walid.autolayout.attr.PaddingRightAttr;
import com.walid.autolayout.attr.PaddingTopAttr;
import com.walid.autolayout.attr.TextSizeAttr;
import com.walid.autolayout.attr.WidthAttr;

import java.util.ArrayList;
import java.util.List;

/**
 * Author   : walid
 * Data     : 2016-08-30  16:25
 * Describe :
 */
public class AutoLayoutInfo {

    private List<AutoAttr> autoAttrs = new ArrayList<>();

    public void addAttr(AutoAttr autoAttr) {
        autoAttrs.add(autoAttr);
    }

    public void fillAttrs(View view) {
        for (AutoAttr autoAttr : autoAttrs) {
            autoAttr.apply(view);
        }
    }

    public static AutoLayoutInfo getAttrFromView(View view, int attrs) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null) {
            return null;
        }
        AutoLayoutInfo autoLayoutInfo = new AutoLayoutInfo();

        // width & height
        if ((attrs & Attrs.WIDTH) != 0 && params.width > 0) {
            autoLayoutInfo.addAttr(WidthAttr.generate(params.width));
        }

        if ((attrs & Attrs.HEIGHT) != 0 && params.height > 0) {
            autoLayoutInfo.addAttr(HeightAttr.generate(params.height));
        }

        //margin
        if (params instanceof ViewGroup.MarginLayoutParams) {
            if ((attrs & Attrs.MARGIN) != 0) {
                autoLayoutInfo.addAttr(MarginLeftAttr.generate(((ViewGroup.MarginLayoutParams) params).leftMargin));
                autoLayoutInfo.addAttr(MarginTopAttr.generate(((ViewGroup.MarginLayoutParams) params).topMargin));
                autoLayoutInfo.addAttr(MarginRightAttr.generate(((ViewGroup.MarginLayoutParams) params).rightMargin));
                autoLayoutInfo.addAttr(MarginBottomAttr.generate(((ViewGroup.MarginLayoutParams) params).bottomMargin));
            }
            if ((attrs & Attrs.MARGIN_LEFT) != 0) {
                autoLayoutInfo.addAttr(MarginLeftAttr.generate(((ViewGroup.MarginLayoutParams) params).leftMargin));
            }
            if ((attrs & Attrs.MARGIN_TOP) != 0) {
                autoLayoutInfo.addAttr(MarginTopAttr.generate(((ViewGroup.MarginLayoutParams) params).topMargin));
            }
            if ((attrs & Attrs.MARGIN_RIGHT) != 0) {
                autoLayoutInfo.addAttr(MarginRightAttr.generate(((ViewGroup.MarginLayoutParams) params).rightMargin));
            }
            if ((attrs & Attrs.MARGIN_BOTTOM) != 0) {
                autoLayoutInfo.addAttr(MarginBottomAttr.generate(((ViewGroup.MarginLayoutParams) params).bottomMargin));
            }
        }

        //padding
        if ((attrs & Attrs.PADDING) != 0) {
            autoLayoutInfo.addAttr(PaddingLeftAttr.generate(view.getPaddingLeft()));
            autoLayoutInfo.addAttr(PaddingTopAttr.generate(view.getPaddingTop()));
            autoLayoutInfo.addAttr(PaddingRightAttr.generate(view.getPaddingRight()));
            autoLayoutInfo.addAttr(PaddingBottomAttr.generate(view.getPaddingBottom()));
        }
        if ((attrs & Attrs.PADDING_LEFT) != 0) {
            autoLayoutInfo.addAttr(MarginLeftAttr.generate(view.getPaddingLeft()));
        }
        if ((attrs & Attrs.PADDING_TOP) != 0) {
            autoLayoutInfo.addAttr(MarginTopAttr.generate(view.getPaddingTop()));
        }
        if ((attrs & Attrs.PADDING_RIGHT) != 0) {
            autoLayoutInfo.addAttr(MarginRightAttr.generate(view.getPaddingRight()));
        }
        if ((attrs & Attrs.PADDING_BOTTOM) != 0) {
            autoLayoutInfo.addAttr(MarginBottomAttr.generate(view.getPaddingBottom()));
        }

        //minWidth ,maxWidth , minHeight , maxHeight
        if ((attrs & Attrs.MIN_WIDTH) != 0) {
            autoLayoutInfo.addAttr(MinWidthAttr.generate(MinWidthAttr.getMinWidth(view)));
        }
        if ((attrs & Attrs.MAX_WIDTH) != 0) {
            autoLayoutInfo.addAttr(MaxWidthAttr.generate(MaxWidthAttr.getMaxWidth(view)));
        }
        if ((attrs & Attrs.MIN_HEIGHT) != 0) {
            autoLayoutInfo.addAttr(MinHeightAttr.generate(MinHeightAttr.getMinHeight(view)));
        }
        if ((attrs & Attrs.MAX_HEIGHT) != 0) {
            autoLayoutInfo.addAttr(MaxHeightAttr.generate(MaxHeightAttr.getMaxHeight(view)));
        }

        //textsize

        if (view instanceof TextView) {
            if ((attrs & Attrs.TEXTSIZE) != 0) {
                autoLayoutInfo.addAttr(TextSizeAttr.generate((int) ((TextView) view).getTextSize()));
            }
        }
        return autoLayoutInfo;
    }


    @Override
    public String toString() {
        return "AutoLayoutInfo{" + "autoAttrs=" + autoAttrs + '}';
    }

}