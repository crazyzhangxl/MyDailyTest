package com.zxl.mydailytest.webview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.zxl.mydailytest.MyApplication;
import com.zxl.mydailytest.R;

public class Main2Activity extends AppCompatActivity {
    private FrameLayout mFrameLayout;
    private WebView mWebView;
    public static void show(Activity activity){
        Intent intent = new Intent(activity,Main2Activity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_web);

        mFrameLayout = findViewById(R.id.frameLayout);
        mWebView = MyApplication.mWebView;
        mFrameLayout.addView(mWebView);
        mWebView.loadUrl("file:///android_asset/protocol2.html");
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
