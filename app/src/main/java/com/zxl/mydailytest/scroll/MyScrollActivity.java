package com.zxl.mydailytest.scroll;

import android.graphics.Rect;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zxl.mydailytest.MyTestAdapter;
import com.zxl.mydailytest.R;
import com.zxl.mydailytest.activities.ShowBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zxl on 2018/07/18.
 *         discription:
 */
public class MyScrollActivity extends AppCompatActivity {
    private RecyclerView mRvScroll;
    private List<ShowBean> mList = new ArrayList<>();
    private BaseQuickAdapter<ShowBean,BaseViewHolder> mAdapter;
    private LinearLayout llBottom;
    private LinearLayoutManager mManager;
    private FloatingActionButton mActionButtonLog;
    private TextView tvResult;

    private int mTouchSlop;
    private int mPageSlop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_scroll);
        initView();
        initSlop();
        initData();
        initListener();
    }

    private void initSlop() {
        mTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        mPageSlop = ViewConfiguration.get(this).getScaledPagingTouchSlop();
        tvResult.setText("touchSlop = "+mTouchSlop+"  pageSlop = "+mPageSlop);
    }

    private void initListener() {
        // 添加滑动监听事件 ---
        mRvScroll.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                boolean isSignificantDelta = Math.abs(dy) >
//                        mTouchSlop;
//                if (isSignificantDelta) {
//                    if (dy > 0) {
//                        hideLogTab();
//                    } else {
//                        showLogTab();
//                    }
//                }
            }
        });



        mRvScroll.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                Log.e("onInterceptTouchEvent","onInterceptTouchEvent执行");
                return false;
            }

            // 可以发现该方法并没有得到调用
            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                /*
                 *  ACTION_DOWN   = 0;
                 *  ACTION_UP     = 1;
                 *  ACTION_MOVE   = 2;
                 */
                Log.d("onTouchEvent","滑动状态:"+rv.getScrollState()+"  触摸状态"+e.getAction());
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        mActionButtonLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyScrollActivity.this, "顶部 = "+isScrollTop()+" , 底部 = "+isScrollBottom(), Toast.LENGTH_SHORT).show();
                Log.e("滑动", "顶部 = "+_isScrollTop2()+" --"+_isScrollTop3()+"  +++++++  底部 = "+_isScrollBottom2()+"---"+_isScrollBottom3() );

            }
        });
    }


    private boolean isScrollTop(){
        if (mManager.findFirstVisibleItemPosition() == 0){
            View view =  mManager.findViewByPosition(0);
            if (view.getTop() == 0){
                return true;
            }
        }
        return false;
    }

    private boolean _isScrollTop2(){
        if (mManager.findFirstCompletelyVisibleItemPosition() == 0){
            return true;
        }
        return false;
    }

    private boolean _isScrollTop3(){
        if (mRvScroll.canScrollVertically(1)){
            return true;
        }
        return false;
    }

    private boolean isScrollBottom(){
        if (mManager.findLastVisibleItemPosition() == mAdapter.getItemCount()-1){
            View view =  mManager.findViewByPosition(mAdapter.getItemCount()-1);
            // 如果添加了分割线  其实不能用这种方法去判断 因为会少了分割线
            if (view.getBottom() == mRvScroll.getHeight()){
                return true;
            }

        }
        return false;
    }

    private boolean _isScrollBottom2(){
        if (mManager.findLastCompletelyVisibleItemPosition() == (mRvScroll.getAdapter().getItemCount()-1)){
            return true;
        }
        return false;
    }

    private boolean _isScrollBottom3(){
        if (mRvScroll.canScrollVertically(-1)){
            return true;
        }
        return false;
    }


    private void showLogTab() {
        mActionButtonLog.setVisibility(View.VISIBLE);
    }

    private void hideLogTab() {
        mActionButtonLog.setVisibility(View.GONE);
    }

    private void initData() {
        for (int i=0;i<50;i++){
            mList.add(new ShowBean("第一个第一个第一个第一个第一个"+i,
                    "第二个第二个第二个第二个"+i,false,false));
        }
        mAdapter.notifyDataSetChanged();
    }

    private void setTreeObserver() {
        // mView只是布局中的一块小的区域
        ViewTreeObserver viewTreeObserver = mRvScroll.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                mRvScroll.getWindowVisibleDisplayFrame(rect);
                Log.e("onGlobalLayout()","矩形左 = "+rect.left+"  矩形上 = "+rect.top +
                        " 矩形右 = "+rect.right +"  矩形下 = "+rect.bottom);
                View view = mManager.findViewByPosition(10);
                if (view != null){
                    TextView tv = (TextView) view.findViewById(R.id.tv);
                    if (tv != null){
                        Log.e("onGlobalLayout()",tv.getText().toString());
                    }
                }
                mRvScroll.getViewTreeObserver().removeOnGlobalLayoutListener(this::onGlobalLayout);
            }
        });
    }

    private void initView() {
        mRvScroll = findViewById(R.id.rv);
        llBottom = findViewById(R.id.llBottom);
        tvResult = findViewById(R.id.tvResult);
        mActionButtonLog = findViewById(R.id.logTab);
        mManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRvScroll.setLayoutManager(mManager);
        mRvScroll.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        View view = new View(this);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,50));
        view.setBackgroundColor(getResources().getColor(R.color.colorAccent));

        mAdapter = new MyTestAdapter(R.layout.item_scroll,mList);
        //mAdapter.addHeaderView(view);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ShowBean showBean = mList.get(position);
                boolean b1 = showBean.isShow();
                boolean b2 = showBean.isShow_suffix();
                if (view.getId() == R.id.tv){
                    showBean.setShow(!b1);
                }else if (view.getId() == R.id.tv2){
                    showBean.setShow_suffix(!b2);
                }
                mAdapter.notifyItemChanged(position);
            }
        });
        mRvScroll.setAdapter(mAdapter);
        setTreeObserver();
    }
}
