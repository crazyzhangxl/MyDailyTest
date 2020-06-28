package com.zxl.mydailytest.viewpager;

import android.app.Activity;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.zxl.mydailytest.R;

public class ViewPagerOneActivity extends AppCompatActivity {

    public static void show(Activity activity){
        Intent intent = new Intent(activity,ViewPagerOneActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_one);
    }
}
