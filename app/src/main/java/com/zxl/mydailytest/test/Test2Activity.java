package com.zxl.mydailytest.test;

import android.app.Activity;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.zxl.mydailytest.R;

public class Test2Activity extends AppCompatActivity {

    public static void show(Activity activity) {
        Intent intent = new Intent(activity, Test2Activity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        for (int i=0;i<100;i++){
            int show = i;
            _show(show);
        }
    }

    private void _show(int show) {
        Log.e("日志", "onCreate: "+ show);
    }
}
