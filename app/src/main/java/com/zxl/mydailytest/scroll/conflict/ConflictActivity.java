package com.zxl.mydailytest.scroll.conflict;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zxl.mydailytest.MyNestedView;
import com.zxl.mydailytest.NoConflictRecyclerView;
import com.zxl.mydailytest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zxl on 2018/07/19.
 *         discription: 这个后面再进行总结吧,浪费太长时间了----已经
 */
public class ConflictActivity extends AppCompatActivity {
    private MyNestedView mNestScrollView;
    private NoConflictRecyclerView mRvConflict;
    private List<String> mData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conflict);
        initData();
        initView();
        initListener();
    }

    private void initListener() {
        mNestScrollView.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {

        });

        mRvConflict.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                Log.e("RV滑动","现在= "+dy);
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void initView() {
        mRvConflict = findViewById(R.id.rv);
        mNestScrollView = findViewById(R.id.mNestView);
        LinearLayoutManager manager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        // 默认为true
        manager.setSmoothScrollbarEnabled(true);
        // 允许自动测量
        manager.setAutoMeasureEnabled(true);
        mRvConflict.setLayoutManager(manager);
        mRvConflict.setHasFixedSize(true);
        mRvConflict.setNestedScrollingEnabled(false);
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_scroll, mData) {
            @Override
            protected void convert(BaseViewHolder helper, String item) {
                helper.setText(R.id.tv, item);
            }
        };
        mRvConflict.setAdapter(baseQuickAdapter);
        baseQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TextView tv = view.findViewById(R.id.tv);
                tv.setLines(10);
                baseQuickAdapter.notifyItemChanged(position);

            }
        });

    }
    private void initData() {
        for (int i=0;i<80;i++){
            mData.add("我闪闪生辉实话实说时候还是少说话少说话说话说话说99999999999999uuuuuuuuuufffhhhhhhhhhhhhhhhhhhhhhhhhhhh"+i);
        }
    }
}
