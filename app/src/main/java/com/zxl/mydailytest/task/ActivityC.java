package com.zxl.mydailytest.task;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zxl.mydailytest.R;

/**
 * @author zxl on 2018/08/20.
 *         discription:
 */
public class ActivityC extends AppCompatActivity {
    private static final String TAG = "SingleTask启动模式";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
        Log.e(TAG,"ActivityC onCreate()");
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityC.this,ActivityD.class));


            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"ActivityC onDestroy()");

    }
}
