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
public class ActivityD extends AppCompatActivity {
    private static final String TAG = "SingleTask启动模式";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d);
        Log.e(TAG,"ActivityD onCreate()");
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityD.this, ActivityB.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG,"ActivityD onDestroy()");

    }

}
