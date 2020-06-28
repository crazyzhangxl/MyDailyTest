package com.zxl.mydailytest;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @author zxl on 2018/07/20.
 *         discription:
 */
public class LocationActivity extends AppCompatActivity {
    private Context mContext;
    private ImageView mIvControl;
    private int mDownX;
    private int mDownY;
    private ImageView mIvLeft;
    private ImageView mIvRight;
    private ImageView mIvTop;
    private ImageView mIvBottom;


    @Override
    protected void onStart() {
        super.onStart();
        if (getIntent() != null) {
            Log.e("LocationActivity", "onStart: " + "活动跳转进来了");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_location);
        mIvControl = findViewById(R.id.ivControl);
        mIvLeft = findViewById(R.id.ivLeft);
        mIvRight = findViewById(R.id.ivRight);
        mIvBottom = findViewById(R.id.ivBottom);
        mIvTop = findViewById(R.id.ivTop);
        mIvControl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    mDownX = (int) event.getX();
                    mDownY = (int) event.getY();

                }
                return false;
            }
        });

        mIvControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doControlClick();
            }
        });
    }

    /**
     * 设置按钮的点击事件
     */
    private void doControlClick() {
        int width = mIvControl.getWidth();
        if ( (mDownY > -1 * mDownX + width) && (mDownY < mDownX)){
            mIvRight.setSelected(true);
            mIvLeft.setSelected(false);
            mIvTop.setSelected(false);
            mIvBottom.setSelected(false);
        }else if ((mDownY < -1 * mDownX + width) && (mDownY < mDownX)){
            mIvRight.setSelected(false);
            mIvLeft.setSelected(false);
            mIvTop.setSelected(true);
            mIvBottom.setSelected(false);
        }else if ((mDownY < -1 * mDownX + width) && (mDownY > mDownX)){
            mIvRight.setSelected(false);
            mIvLeft.setSelected(true);
            mIvTop.setSelected(false);
            mIvBottom.setSelected(false);
        }else {
            mIvRight.setSelected(false);
            mIvLeft.setSelected(false);
            mIvTop.setSelected(false);
            mIvBottom.setSelected(true);
        }

    }

}
