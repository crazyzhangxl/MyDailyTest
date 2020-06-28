package com.zxl.mydailytest.recycle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.zxl.mydailytest.R;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;

/**
 * @author crazyZhangxl on 2018-12-10 15:58:49.
 * Describe:
 */

public class RecyclerActivity extends AppCompatActivity implements RecycleAdapter.RecyclerListener {
    private RecyclerView mRecyclerView;
    private RecycleAdapter mRecycleAdapter;
    private List<RecycleBean> mRecycleBeanList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        init();
        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecycleAdapter = new RecycleAdapter(this,this);
        mRecycleAdapter.setData(mRecycleBeanList);
        mRecyclerView.setAdapter(mRecycleAdapter);
    }

    private void init() {
        mRecycleBeanList = new ArrayList<>();
        mRecycleBeanList.add(new RecycleBean(1,1));
        mRecycleBeanList.add(new RecycleBean(2,2));
        mRecycleBeanList.add(new RecycleBean(3,3));
        mRecycleBeanList.add(new RecycleBean(10,10));
        mRecycleBeanList.add(new RecycleBean(9,9));
        mRecycleBeanList.add(new RecycleBean(8,8));
        mRecycleBeanList.add(new RecycleBean(7,7));
        mRecycleBeanList.add(new RecycleBean(6,6));
        mRecycleBeanList.add(new RecycleBean(5,5));
    }

    @Override
    public void onFirstBtnClick(int position) {
        int first = mRecycleBeanList.get(position).getFirst();
        mRecycleBeanList.get(position).setFirst(first+1);
        int second = mRecycleBeanList.get(position).getSecond();
        mRecycleBeanList.get(position).setSecond(second+1);
        mRecycleAdapter.notifyItemChanged(position,1);
    }

    @Override
    public void onSecondBtnClick(int position) {
        int first = mRecycleBeanList.get(position).getFirst();
        mRecycleBeanList.get(position).setFirst(first+1);
        int second = mRecycleBeanList.get(position).getSecond();
        mRecycleBeanList.get(position).setSecond(second+1);
        mRecycleAdapter.notifyItemChanged(position,2);
    }

    @Override
    public void onAllCBtnClick(int position) {
        int first = mRecycleBeanList.get(position).getFirst();
        mRecycleBeanList.get(position).setFirst(first+1);
        int second = mRecycleBeanList.get(position).getSecond();
        mRecycleBeanList.get(position).setSecond(second+1);
        mRecycleAdapter.notifyItemChanged(position);
    }
}
