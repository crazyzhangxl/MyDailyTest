package com.zxl.mydailytest.activities;

import android.graphics.drawable.BitmapDrawable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.zxl.mydailytest.R;

/**
 * @author crazyZhangxl on 2018-12-23 16:27:25.
 * Describe:
 */

public class PopupWindowActivity extends AppCompatActivity {
    private Button mBtnShow,mBtnSample;
    private PopupWindow mIvOptionPopupWindow;
    private RelativeLayout mRl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        initViews();
    }

    private void initViews() {
        mBtnShow = findViewById(R.id.btnShow);
        mBtnSample = findViewById(R.id.sampleEasy);
        mRl = findViewById(R.id.rlShow);
        mBtnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(mBtnShow);
            }
        });

        mBtnSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopSample();
            }
        });
    }

    /**
     *  popupWindow简单案例 ------
     */
    private void showPopSample() {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(100,50));
        linearLayout.setBackground(getResources().getDrawable(R.mipmap.ic_launcher_round));
        PopupWindow popupWindow = new PopupWindow(linearLayout, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(mBtnSample, 0, 0);
    }

    /**
     * ivOption点击后显示popupWindow
     *
     * @param ivOption
     */
    protected void showPopupWindow(View ivOption) {
        int mHeight = mRl.getHeight();
        if (mIvOptionPopupWindow == null) {
            View contentView = View.inflate(this, R.layout.item_room, null);
            Log.e("11", "showPopupWindow: "+mHeight );
            mIvOptionPopupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                    true);
        }

        // 点击popupwindow范围以外的地方时隐藏
        mIvOptionPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mIvOptionPopupWindow.setOutsideTouchable(true);

        // 控制它放置的位置
        if (isShowBottom(ivOption,mHeight)) {// 显示popupwindow在itemView的下方，偏移量都为0
            mIvOptionPopupWindow.showAsDropDown(ivOption, 0, 0);
        } else {// 显示popupwindow在itemView的上方，偏移量y都为-2*itemView.getHeight()
            mIvOptionPopupWindow.showAsDropDown(ivOption, 0,
                    -(ivOption.getHeight()+mHeight));
        }
    }

    /**
     * 判断popupWindow是否显示在条目的下方
     * @param itemView  需要显示的相对位置
     * @param mHeight 显示布局的高度 这里要显示的高度无法测量啊------
     * @return
     */
    private boolean isShowBottom(View itemView,int mHeight) {
        // 得到屏幕的高度
        // int heightPixels =
        // getResources().getDisplayMetrics().heightPixels;//方式1
        int screenHeight = getWindowManager().getDefaultDisplay().getHeight();// 方式2

        int[] location = new int[2];
        // location[0]-->x
        // location[1]-->y
        itemView.getLocationInWindow(location);
        // 得到itemView在屏幕中Y轴的值
        int itemViewY = location[1];

        // 得到itemView距离屏幕底部的距离
        int distance = screenHeight - itemViewY - itemView.getHeight();
        Log.e("11", "isShowBottom: "+distance );
        Log.e("11", "isShowBottom2222222222222: "+mHeight );

        if (distance < mHeight) {// 条目下方放不下popupWindow
            return false;
        } else {// 条目下方放得下popupWindow
            return true;
        }
    }

}
