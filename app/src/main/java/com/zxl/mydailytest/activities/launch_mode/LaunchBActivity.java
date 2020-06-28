package com.zxl.mydailytest.activities.launch_mode;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.zxl.mydailytest.R;

public class LaunchBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_b);
        Log.e("启动模式", "活动B onCreate" );

        findViewById(R.id.btnClear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LaunchAActivity.showClear(LaunchBActivity.this);
            }
        });

        findViewById(R.id.btnSingle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LaunchAActivity.showB(LaunchBActivity.this);
            }
        });

        findViewById(R.id.btnBound).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LaunchAActivity.showBound(LaunchBActivity.this);
            }
        });

        findViewById(R.id.task).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LaunchBActivity.this,LaunchAActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });

        findViewById(R.id.jumpYanchang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMethod2();
            }
        });
    }

    private void showMethod1(){
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.zxl.mydailytest");
        launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        launchIntent.setClassName("com.example.apple.scrolldemo","com.example.apple.scrolldemo.MainActivity");
        startActivity(launchIntent);
    }

    private void showMethod2(){
        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setComponent(new ComponentName("com.example.apple.scrolldemo","com.example.apple.scrolldemo.MainActivity"));
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("启动模式", "活动B onDestroy" );
    }
}
