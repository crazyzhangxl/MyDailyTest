package com.zxl.mydailytest;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.zxl.mydailytest.multi_thread.InitService;


/**
 * @author zxl on 2018/8/15.
 *         discription:
 */

public class MyApplication extends Application {
    public static Context mContext = null;
    public static WebView mWebView;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        // 不需要放在主线程的可以放在intentService中
        startService(new Intent(this, InitService.class));
        initWebViews();
    }

    public static Context getContext() {
        return mContext;
    }

    private void initWebViews() {
        mWebView = new WebView(this);
        WebSettings setting = mWebView.getSettings();
        setting.setJavaScriptEnabled(true);
        setting.setUseWideViewPort(true);
        setting.setLoadWithOverviewMode(true);
        setting.setBuiltInZoomControls(true);
        setting.setSupportZoom(true);
        setting.setDisplayZoomControls(false);
        setting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        setting.setJavaScriptCanOpenWindowsAutomatically(true);
        setting.setAppCacheEnabled(true);
        setting.setDomStorageEnabled(true);
        setting.setGeolocationEnabled(true);
        setting.setAppCacheMaxSize(Long.MAX_VALUE);
        setting.setPluginState(WebSettings.PluginState.ON_DEMAND);
        setting.setCacheMode(WebSettings.LOAD_NO_CACHE);
        setting.setAllowFileAccess(true);
        setting.setAllowFileAccessFromFileURLs(true);
        setting.setAllowUniversalAccessFromFileURLs(true);
        setting.setAllowContentAccess(true);
        setting.setBlockNetworkImage(true);
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.setVerticalScrollBarEnabled(false);
    }
}
