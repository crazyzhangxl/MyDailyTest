package com.zxl.mydailytest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.core.widget.NestedScrollView;


/**
 * @author zxl on 2018/7/19.
 *         discription:
 */

public class MyNestedView extends NestedScrollView {
    private int nowY = 0;
    public MyNestedView(Context context) {
        super(context);
    }

    public MyNestedView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNestedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        nowY = oldt;
        Log.e("SV滑动","现在= "+l + "    之前= "+oldt);
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
