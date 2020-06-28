package com.zxl.mydailytest.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import org.jetbrains.annotations.Nullable;

/**
 * @author crazyZhangxl on 2018/11/10.
 * Describe: 测试onTouchEvent参数变化
 */
public class TestTouchView extends View {
    private int mDefaultHeight = dp2px(100);
    private int mDefaultWidth = dp2px(200);
    private static final String TAG = "TestTouchView";

    public TestTouchView(Context context) {
        this(context,null);
    }

    public TestTouchView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public TestTouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e("测试","dispatchTouchEvent  = "+super.dispatchTouchEvent(event));
        return super.dispatchTouchEvent(event);
    }


    /**
     * 测试滑动是否响应 -----
     *
     * 在这里默认 onTouchEvent 返回值是返回false的,
     * 现在我只需要片面的认为只有返回true 那么事件才能够传递下去
     *
     * 至于只有down返回true move返回false仍然可以传递就不清楚了
     * @param event
     * @return
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG,"ACTION_DOWN == "+event.getX());
                return true;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG,"ACTION_MOVE == "+event.getX());
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG,"ACTION_UP == "+event.getX());
                break;
                default:break;
        }
        Log.e("测试","onTouchEvent  =  "+super.onTouchEvent(event)+"");
        return super.onTouchEvent(event);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int resultHeight = heightSize;
        int resultWidth = widthSize;
        if (MeasureSpec.AT_MOST == widthMode){
            resultWidth = mDefaultWidth;
        }

        if (MeasureSpec.AT_MOST == heightMode){
            resultHeight = mDefaultHeight;
        }

        setMeasuredDimension(resultWidth,resultHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制一下背景
        canvas.drawColor(Color.RED);
    }

    private int dp2px(int xDp){
        // 屏幕像素比
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int)(scale*xDp + 0.5);

    }
}
