package com.zxl.mydailytest;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zxl on 2018/07/18.
 *         discription: 表格学习
 */
public class TableActivity extends AppCompatActivity {
    private RecyclerView mRvLeft;
    private RecyclerView mRvRight;
    private List<Object> mData = new ArrayList();
    private ObservableHorizontalScrollView mSvRoom;
    private boolean isLeftOk = false;
    private boolean isRightOk = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);
        initViews();
        initData();
        initListener();

    }

    /**
     *  滑动的过程 -------------
     */

    private final RecyclerView.OnScrollListener mRightOSL = new MyOnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            mRvLeft.scrollBy(dx,dy);
        }
    };

        private final RecyclerView.OnScrollListener mLeftOSL = new MyOnScrollListener(){
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            mRvRight.scrollBy(dx,dy);
        }
    };




    private void initViews() {
        mRvLeft = (RecyclerView) findViewById(R.id.rvLeft);
        mRvRight = (RecyclerView) findViewById(R.id.rvRight);
        mSvRoom = findViewById(R.id.sv_room);

        mRvLeft.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRvRight.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        // 可以学一下 人家这个建造者模式
        mRvLeft.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .color(Color.parseColor("#f5f5f5"))
                .size(1)
                .build()
        );
        mRvRight.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .color(Color.parseColor("#f5f5f5"))
                .size(1)
                .build()
        );

        mRvRight.addOnScrollListener(new RecyclerView.OnScrollListener() {
            /**
             * 滑动的状态
             * @param recyclerView
             * @param newState
             */
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            /**
             * 默认为0,即静止状态----------
             * @param recyclerView
             * @param dx
             * @param dy
             */
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e("滑动","============滑动中============");
                Log.e("滑动","------  "+recyclerView.getScrollState()+"  -------");
            }
        });
    }

    private void initData() {
        int layerCount = 30;
        for (int i=0;i<layerCount;i++){
            mData.add(new Object());
        }

        mRvLeft.setAdapter(new BaseQuickAdapter<Object,BaseViewHolder>(R.layout.item_room,mData) {
            @Override
            protected void convert(BaseViewHolder helper, Object item) {

            }
        });

        mRvRight.setAdapter(new BaseQuickAdapter<Object,BaseViewHolder>(R.layout.item_layer,mData) {
            @Override
            protected void convert(BaseViewHolder helper, Object item) {
                LinearLayout llLayer = helper.getView(R.id.ll_layer);
                llLayer.removeAllViews();
                for (int i=0;i<7;i++){
                    View view = LayoutInflater.from(TableActivity.this).inflate(R.layout.item_room,null);
                    View line = new View(TableActivity.this);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(1, LinearLayout.LayoutParams.MATCH_PARENT);
                    line.setLayoutParams(params);
                    line.setBackgroundColor(Color.parseColor("#f5f5f5"));
                    llLayer.addView(view);
                    llLayer.addView(line);
                }
            }

        });
    }

    private void initListener() {
        setSyncScrollListener();
    }


    /**
     * 设置两个列表的同步滚动
     */
    private void setSyncScrollListener(){



        mRvLeft.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            private int mLastY;

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                // 我感觉是在滑动的时候 -- 这里才会执行
                Log.e("空闲","////// ----触发调遣时间");
                // 当列表是静止状态时
                //------------一开始确实是静止的-
                if (rv.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    // 确实是开始滚动
                    Log.e("空闲","==============正在空闲呢==============");
                    onTouchEvent(rv, e);
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                // 若是手指按下的动作，且另一个列表处于空闲状态
                // 摁下即将开始滑动,故而设置监听
                if (e.getAction() == MotionEvent.ACTION_DOWN
                        && mRvRight.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    // 记录当前另一个列表的y坐标并对当前列表设置滚动监听
                    mLastY = rv.getScrollY();
                    rv.addOnScrollListener(mLeftOSL);
                    isLeftOk = true;
                    Log.e("空闲","*****= 设置监听= *****");
                } else {
                    // 若当前列表原地抬起手指时，移除当前列表的滚动监听
                    if (e.getAction() == MotionEvent.ACTION_UP && rv.getScrollY() == mLastY) {
                        rv.removeOnScrollListener(mLeftOSL);
                        isLeftOk = false;
                        Log.e("空闲","&&&& = 移除监听= &&&&");
                    }
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        mRvRight.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            private int mLastY;

            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                Log.e("判断","左边: "+isLeftOk +"  "+"右边  "+isRightOk);
                if (rv.getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    onTouchEvent(rv, e);
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                if (e.getAction() == MotionEvent.ACTION_DOWN && mRvLeft.getScrollState()
                        == RecyclerView.SCROLL_STATE_IDLE) {
                    mLastY = rv.getScrollY();
                    rv.addOnScrollListener(mRightOSL);
                    isRightOk = true;
                } else {
                    if (e.getAction() == MotionEvent.ACTION_UP && rv.getScrollY() == mLastY) {
                        rv.removeOnScrollListener(mRightOSL);
                        isRightOk = false;
                    }
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        mSvRoom.setScrollViewListener(new ObservableHorizontalScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableHorizontalScrollView scrollView, int x, int y, int oldx, int oldy) {
                // 当 滑动时取消监听
                mRvLeft.removeOnScrollListener(mLeftOSL);
                mRvRight.removeOnScrollListener(mRightOSL);
            }
        });

    }
}
