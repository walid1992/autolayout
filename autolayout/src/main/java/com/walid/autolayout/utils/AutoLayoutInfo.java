package com.walid.autolayout.utils;

import android.view.View;

import com.walid.autolayout.attr.AutoAttr;

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

    @Override
    public String toString() {
        return "AutoLayoutInfo{" + "autoAttrs=" + autoAttrs + '}';
    }

}