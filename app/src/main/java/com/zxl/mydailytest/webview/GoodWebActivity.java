package com.zxl.mydailytest.webview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.zxl.mydailytest.MyApplication;
import com.zxl.mydailytest.R;

import java.util.Date;

public class GoodWebActivity extends AppCompatActivity {
    private FrameLayout mFrameLayout;
    private WebView mWebView;
    private long startTime;
    private long endTime;
    public static void show(Activity activity){
        Intent intent = new Intent(activity,GoodWebActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_web);
        startTime = System.currentTimeMillis();
        mFrameLayout = findViewById(R.id.frameLayout);
        mWebView = MyApplication.mWebView;
        mFrameLayout.addView(mWebView);
        mWebView.loadUrl("file:///android_asset/protocol.html");
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

    @Override
    protected void onDestroy() {
        if (mWebView != null){
            mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            mWebView.clearHistory();
            ViewGroup parent = (ViewGroup) mWebView.getParent();
            if (parent != null) {
                parent.removeView(mWebView);
            }
        }
        super.onDestroy();

    }
}
