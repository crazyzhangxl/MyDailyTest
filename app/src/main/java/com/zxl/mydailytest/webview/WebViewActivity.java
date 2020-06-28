package com.zxl.mydailytest.webview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.zxl.mydailytest.R;


/**
 * 普通webview
 *
 * 这样是错的啊
 */
public class WebViewActivity extends AppCompatActivity {
    private WebView mWebView;
    private long startTime;
    private long endTime;
    private long _startTime,_endTime;
    private LinearLayout root;

    public static void show(Activity activity){
        Intent intent = new Intent(activity,WebViewActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        startTime = System.currentTimeMillis();
        root = findViewById(R.id.root);
        // webView显示...
        _startTime = System.currentTimeMillis();
        mWebView = new WebView(getApplicationContext());
        mWebView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
        root.addView(mWebView);
        _endTime = System.currentTimeMillis();
        long _distance = _endTime -_startTime;
        Log.e("web", "统计时间2: "+_distance );
        initSettings();
        mWebView.loadUrl("file:///android_asset/protocol.html");
    }

    /**
     * 这些初始化只消耗了 5ms
     */
    private void initSettings() {
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

        // 加载出来用了2796ms
        // webView.setWebChromeClient(x)
        mWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100){
                    endTime = System.currentTimeMillis();
                    long distance = endTime - startTime;
                    Log.e("web", "onProgressChanged: "+ distance);
                }
            }
        });

    }
}
