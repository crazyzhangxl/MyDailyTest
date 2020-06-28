package com.zxl.mydailytest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * @author zxl on 2018/7/20.
 *         discription:
 */

public class ProcessLinearLayout extends LinearLayout {
    public ProcessLinearLayout(Context context) {
        this(context,null);
    }

    public ProcessLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ProcessLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        Log.e("执行顺序","---- init() -----");
        Log.e("执行顺序","init() 子View = "+getChildAt(0));
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.e("执行顺序","---- onFinishInflate() -----");
        View view = getChildAt(0);
        Log.e("执行顺序","onFinishInflate() 子View = "+view+"   高度 = "+view.getHeight());

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("执行顺序","---- onMeasure() -----");
        View view = getChildAt(0);
        Log.e("执行顺序","onMeasure() 子View = "+view+"   高度 = "+view.getHeight());
        Log.e("执行顺序","onMeasure() 布局高度 "+getHeight());
        post(new Runnable() {
            @Override
            public void run() {
                View view2 = getChildAt(0);
                Log.e("执行顺序","post() 子View = "+view2+"   高度 = "+view2.getHeight());
                Log.e("执行顺序","post() 布局高度 "+getHeight());
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.e("执行顺序","---- onLayout() -----");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e("执行顺序","---- onSizeChanged() -----");
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        Log.e("执行顺序","---- onWindowFocusChanged -----");
    }
}
