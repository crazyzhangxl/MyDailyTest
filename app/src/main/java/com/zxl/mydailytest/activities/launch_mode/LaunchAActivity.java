package com.zxl.mydailytest.activities.launch_mode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zxl.mydailytest.R;


public class LaunchAActivity extends AppCompatActivity {
    private TextView tvText;


    public static void showClear(Activity activity){
        Log.e("启动模式", "FLAG_ACTIVITY_CLEAR_TOP" );
        Intent intent = new Intent(activity,LaunchAActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
    }

    public static void showB(Activity activity){
        Log.e("启动模式", "FLAG_ACTIVITY_SINGLE_TOP" );
        Intent intent = new Intent(activity,LaunchAActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
    }

    public static void showBound(Activity activity){
        Log.e("启动模式", "Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP" );
        Intent intent = new Intent(activity,LaunchAActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        activity.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_a);
        Log.e("启动模式", "活动A onCreate" );

        tvText = findViewById(R.id.tvDes);
        setTextDes();
        findViewById(R.id.btnJumpMe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showClear(LaunchAActivity.this);
            }
        });

        findViewById(R.id.btnJump).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showB(LaunchAActivity.this);
            }
        });

        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LaunchAActivity.this,LaunchBActivity.class));
            }
        });
    }

    private void setTextDes() {
        tvText.setText("设置了描述");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Log.e("启动模式", "活动A onNewIntent" );
        super.onNewIntent(intent);
    }

    @Override
    protected void onDestroy() {
        Log.e("启动模式", "活动A onDestroy");
        super.onDestroy();
    }
}
