package com.walid.sample.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.walid.autolayout.view.AutoRelativeLayout;
import com.walid.sample.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author   : walid
 * Data     : 2016-08-23  11:56
 * Describe : common cell 封装
 */
public class CommonCell extends AutoRelativeLayout {

    @Bind(R.id.tv_left_item)
    TextView tvLeftItem;
    @Bind(R.id.tv_right_item)
    TextView tvRightItem;
    @Bind(R.id.iv_right_item)
    ImageView ivRightItem;
    @Bind(R.id.rl_right_item)
    RelativeLayout rlRightItem;
    @Bind(R.id.rl_cell)
    RelativeLayout rlCell;

    public CommonCell(Context context) {
        super(context);
    }

    public CommonCell(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.widget_common_cell, this);
        ButterKnife.bind(this, view);
        updateStyle(context, attrs);
    }

    private void updateStyle(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CommonCell);
        String leftItem = a.getString(R.styleable.CommonCell_CommonCell_tv_left_item);
        String rightItem = a.getString(R.styleable.CommonCell_CommonCell_tv_right_item);
        a.recycle();
        setTvLeftItem(leftItem);
        setTvRightItem(rightItem);
    }

    public void setTvLeftItem(String content) {
        tvLeftItem.setText(content);
    }

    public void setTvRightItem(String content) {
        tvRightItem.setText(content);
        tvRightItem.setVisibility(TextUtils.isEmpty(content) ? GONE : VISIBLE);
    }

}
