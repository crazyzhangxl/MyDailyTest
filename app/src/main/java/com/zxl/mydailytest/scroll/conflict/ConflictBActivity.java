package com.zxl.mydailytest.scroll.conflict;

import android.app.Activity;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.zxl.mydailytest.R;

import java.util.ArrayList;
import java.util.List;


/**
 *
 */
public class ConflictBActivity extends AppCompatActivity {

    private ListView mListView;

    public static void show(Activity activity){
        Intent intent = new Intent(activity,ConflictBActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conflicat_b);
        initViewsWithData();
    }

    private void initViewsWithData() {
        mListView = findViewById(R.id.listView);
        List<String> data = new ArrayList<>();
        for (int i=0;i<100;i++){
            data.add("内部固定高度："+i);
        }
        mListView.setAdapter(new LvAdapter(data,this));
    }
}
