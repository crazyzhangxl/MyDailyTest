package com.zxl.mydailytest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

/**
 * @author zxl on 2018/7/19.
 *         discription:
 */

public class MyDvRelativeLayout extends RelativeLayout {
    public MyDvRelativeLayout(Context context) {
        super(context);
    }

    public MyDvRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyDvRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("事件分发","父ViewGroup dispatchTouchEvent");
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("事件分发","父ViewGroup onInterceptTouchEvent   "+super.onInterceptTouchEvent(ev));
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("事件分发","父ViewGroup onTouchEvent");
        return super.onTouchEvent(event);
    }
}
