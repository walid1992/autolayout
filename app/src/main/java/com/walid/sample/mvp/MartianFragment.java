package com.walid.sample.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.walid.sample.utils.Logger;
import com.walid.sample.vh.MartianViewHolder;

import butterknife.ButterKnife;

public abstract class MartianFragment<TP extends MartianPersenter> extends Fragment {

    protected final String TAG = getClass().getSimpleName();
    protected Activity activity = null;
    protected Bundle savedInstanceState;
    protected TP presenter;
    // 好处延迟加载,用时在去findviewbyid,而且内部也做了缓存
    protected MartianViewHolder viewHolder;
    private View rootView;

    // 是否初始化成功
    private boolean isPrepared;
    // 第一次onResume中的调用onUserVisible避免操作与onFirstUserVisible操作重复
    private boolean isFirstResume = true;
    private boolean isFirstVisible = true;
    private boolean isFirstInvisible = true;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        presenter = createPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getRootLayoutRes(), container, false);
        }
        viewHolder = new MartianViewHolder(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, rootView);
        initViewsAndEvents();

    }

    @Override
    public final void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = getActivity();
        initPrepare();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstResume) {
            isFirstResume = false;
            return;
        }
        if (getUserVisibleHint()) {
            onUserVisible();
        }
    }

    protected abstract TP createPresenter();

    protected abstract void initViewsAndEvents();

    protected abstract void initData();

    @LayoutRes
    protected abstract int getRootLayoutRes();

    protected boolean interceptNavigationClick() {
        return false;
    }

    public void finish() {
        if (activity == null) {
            return;
        }
        activity.finish();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isFirstVisible) {
                isFirstVisible = false;
                initPrepare();
            } else {
                onUserVisible();
            }
        } else {
            if (isFirstInvisible) {
                isFirstInvisible = false;
                onFirstUserInvisible();
            } else {
                onUserInvisible();
            }
        }
    }

    public synchronized void initPrepare() {
        if (isPrepared) {
            onFirstUserVisible();
        } else {
            isPrepared = true;
        }
    }

    // 第一次fragment可见（进行初始化工作）
    public void onFirstUserVisible() {
        initData();
    }

    // fragment可见（切换回来或者onResume）
    public void onUserVisible() {
        Logger.d("fragment可见（切换回来或者onResume）");
    }

    // 第一次fragment不可见（不建议在此处理事件）
    public void onFirstUserInvisible() {
        Logger.d("第一次fragment不可见（不建议在此处理事件）");
    }

    // fragment不可见（切换掉或者onPause）
    public void onUserInvisible() {
        Logger.d("fragment不可见（切换掉或者onPause）");
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint()) {
            onUserInvisible();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (rootView == null) {
            return;
        }
        ViewGroup viewGroup = (ViewGroup) rootView.getParent();
        if (viewGroup == null) {
            return;
        }
        viewGroup.removeView(rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        activity = null;
    }

}
