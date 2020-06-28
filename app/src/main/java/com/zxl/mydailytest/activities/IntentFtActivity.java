package com.zxl.mydailytest.activities;

import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zxl.mydailytest.R;
import com.zxl.mydailytest.activities.launch_mode.LaunchAActivity;

/**
 * @author crazyZhangxl on 2018-12-23 12:35:45.
 * Describe:意图活动
 */

public class IntentFtActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_ft);

        // 卸载用的比较少吧
        findViewById(R.id.uninstall).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doUninstall();
            }
        });

        findViewById(R.id.btnIntent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("IntentFtActivity", "执行跳转");
                jumpToLocationAvy();
            }
        });

        findViewById(R.id.launchMode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntentFtActivity.this, LaunchAActivity.class));
            }
        });
    }

    void jumpToLocationAvy(){
        Intent intent = new Intent();
        intent.setAction("com.zxl.filter");
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        startActivity(intent);
    }


    /**
     *
     */
    void doUninstall(){
    }
}

