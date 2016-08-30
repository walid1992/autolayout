package com.walid.autolayout;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import com.walid.autolayout.utils.AutoLayoutUtils;

/**
 * Author   : walid
 * Data     : 2016-08-30  15:55
 * Describe :
 */
public class AutoLayoutActivity extends AppCompatActivity {

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = AutoLayoutUtils.genAutoView(name, context, attrs);
        if (view != null) {
            return view;
        }
        return super.onCreateView(name, context, attrs);
    }

}
