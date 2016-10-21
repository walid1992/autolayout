package com.walid.sample.page.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.walid.sample.R;
import com.walid.sample.mvp.MartianFragment;
import com.walid.sample.page.web.WebActivity;
import com.walid.sample.utils.RxBindingUtils;
import com.walid.sample.widget.MartianCell;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Author   : walid
 * Data     : 2016-08-23  16:34
 * Describe : 个人中心业务
 */

public class MineFragment extends MartianFragment<MinePresenter> implements IMineView {

    @BindView(R.id.commoncell_alipay)
    MartianCell commoncellAlipay;
    @BindView(R.id.commoncell_wechat)
    MartianCell commoncellWechat;
    @BindView(R.id.commoncell_qq)
    MartianCell commoncellQq;
    @BindView(R.id.commoncell_csdn)
    MartianCell commoncellCsdn;
    @BindView(R.id.commoncell_jianshu)
    MartianCell commoncellJianshu;
    @BindView(R.id.commoncell_gitbub)
    MartianCell commoncellGitbub;

    protected int getRootLayoutRes() {
        return R.layout.fragment_mine;
    }

    @Override
    protected MinePresenter createPresenter() {
        return new MinePresenter(this);
    }

    @Override
    protected void initViewsAndEvents() {
        commoncellAlipay.setEnabled(false);
        commoncellWechat.setEnabled(false);
        commoncellQq.setEnabled(false);
        RxBindingUtils.clicks(aVoid -> {
            WebActivity.startUp("http://blog.csdn.net/walid1992");
        }, commoncellCsdn);

        RxBindingUtils.clicks(aVoid -> {
            WebActivity.startUp("http://www.jianshu.com/users/a279a2f8ed63/latest_articles");
        }, commoncellJianshu);

        RxBindingUtils.clicks(aVoid -> {
            WebActivity.startUp("https://github.com/walid1992");
        }, commoncellGitbub);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onUserVisible() {
        super.onUserVisible();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
