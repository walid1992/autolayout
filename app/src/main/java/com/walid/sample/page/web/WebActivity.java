package com.walid.sample.page.web;

import android.os.Bundle;

import com.walid.sample.R;
import com.walid.sample.mvp.MartianActivity;
import com.walid.sample.utils.ActivityUtils;

/**
 * Author   : walid
 * Data     : 2016-09-20  21:59
 * Describe : WebActivity
 */
public class WebActivity extends MartianActivity<WebPresenter> implements IWebView {

    private static final String WEB_URL = "WEB_URL";

    @Override
    protected WebPresenter createPresenter() {
        return new WebPresenter(this);
    }

    @Override
    protected void bindEvent() {

    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setContentView(R.layout.activity_web);
        ProgressBarWebView webView = viewHolder.getView(R.id.webView);
        webView.setWebViewClient(new MartianWebViewClient() {
            @Override
            public String onPageError(String url) {
                return "file:///android_asset/error.html";
            }
        });
        webView.loadUrl(getIntent().getStringExtra(WEB_URL));
    }

    public static void startUp(String url) {
        ActivityUtils.jump(WebActivity.class, intent -> {
            intent.putExtra(WEB_URL, url);
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ProgressBarWebView progressBarWebView = viewHolder.getView(R.id.webView);
        if (progressBarWebView.getWebView() != null) {
            progressBarWebView.getWebView().destroy();
        }
    }

}
