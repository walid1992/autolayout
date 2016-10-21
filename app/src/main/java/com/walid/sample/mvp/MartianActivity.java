package com.walid.sample.mvp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import com.walid.autolayout.utils.AutoUtils;
import com.walid.sample.vh.MartianViewHolder;

import butterknife.ButterKnife;

/**
 * Author   : walid
 * Data     : 2016-08-31  16:15
 * Describe :
 */

public abstract class MartianActivity<TP extends MartianPersenter> extends AppCompatActivity {

    protected final String TAG = this.getClass().getSimpleName();
    protected TP presenter;
    // 好处延迟加载,用时在去findviewbyid,而且内部也做了缓存
    protected MartianViewHolder viewHolder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        init(savedInstanceState);
    }

    protected abstract TP createPresenter();

    protected abstract void init(Bundle savedInstanceState);

    protected abstract void bindEvent();

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        viewHolder = new MartianViewHolder(getWindow().getDecorView());
        ButterKnife.bind(this);
        bindEvent();
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = AutoUtils.genAutoView(name, context, attrs);
        if (view != null) {
            return view;
        }
        return super.onCreateView(name, context, attrs);
    }

}
