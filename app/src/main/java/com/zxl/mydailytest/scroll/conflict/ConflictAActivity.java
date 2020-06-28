package com.zxl.mydailytest.scroll.conflict;

import android.app.Activity;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.zxl.mydailytest.R;

import java.util.ArrayList;
import java.util.List;


public class ConflictAActivity extends AppCompatActivity {

    private ListView mListView;
    private LvAdapter mLvAdapter;
    private List<String> mData = new ArrayList<>();

    /**
     * 页面跳转...
     * @param activity
     */
    public static void show(Activity activity){
        Intent intent = new Intent(activity,ConflictAActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conflict_a);
        initViews();
        initData();



    }

    private void initData() {
        mData.clear();
        for (int i=0;i<50;i++){
            mData.add("数据 "+i);
        }
        mLvAdapter.notifyDataSetChanged();
    }

    private void initViews() {
        mListView = findViewById(R.id.listView);
        mLvAdapter = new LvAdapter(mData,this);
        mListView.setAdapter(mLvAdapter);
    }
}
