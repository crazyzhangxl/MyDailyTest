package com.zxl.mydailytest.viewpager.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.annotation.SuppressLint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2019-11-11.
 * description: 日历view
 */
public class CalendarLikeView extends View implements View.OnClickListener {

    private List<String> mData = new ArrayList<>();
    private Paint mTextPaint;
    private Paint mBordPaint;
    private int itemHeight,itemWidth,mTextBaseLineHeight;
    private float innerWidth;
    private int dataLength;
    private float mX;
    private float mY;
    private boolean isClick;

    public CalendarLikeView(Context context) {
        this(context,null);
    }

    public CalendarLikeView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public CalendarLikeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        initData();
    }

    private void initData() {
        mData = new ArrayList<>();
        mData.add("1");
        mData.add("2");
        mData.add("3");
        mData.add("4");
        mData.add("5");
        mData.add("6");
        mData.add("7");
        dataLength = mData.size();
    }

    private void init() {
        mTextPaint = new Paint();
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(spToPx(16));
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        setOnClickListener(this);

        mBordPaint = new Paint();
        mBordPaint.setColor(Color.RED);
        mBordPaint.setStrokeWidth(2);
        mBordPaint.setStyle(Paint.Style.STROKE);
    }

    /**
     * 获得控件宽高的时机...
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e("view", getMeasuredHeight()+"---"+getMeasuredWidth() );
        itemHeight = getMeasuredHeight();
        itemWidth = getMeasuredWidth();
        getBaseLineHeight();
    }

    private void getBaseLineHeight() {
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        float centerBaseDistance = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.descent;
        mTextBaseLineHeight = (int) (centerBaseDistance+itemHeight/2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制屏幕背景...
        canvas.drawColor(Color.GRAY);
        innerWidth = itemWidth * 1f/dataLength;
        for (int i=0;i<dataLength;i++){
            float leftWith = i*innerWidth + innerWidth/2;
            // 绘制文本,基线计算...
            canvas.drawText(mData.get(i),leftWith,mTextBaseLineHeight,mTextPaint);
            if (i !=0){
                canvas.drawLine(i*innerWidth,0,i*innerWidth,itemHeight,mBordPaint);
            }
        }
        canvas.drawRect(0,0,itemWidth,itemHeight,mBordPaint);

    }

    /**
     * 根据触摸滑动距离判断是否是点击事件....
     * 针对某些具体的Item...
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getPointerCount() > 1){
            return false;
        }
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mX = event.getX();
                mY = event.getY();
                isClick = true;
                break;
            case MotionEvent.ACTION_MOVE:
                float mDY,mDx;
                if (isClick) {
                    mDY = event.getY() - mY;
                    mDx = event.getX() - mX;
                    isClick = (Math.abs(mDY) <= 50) && (Math.abs(mDx) <= 50);
                }
                break;
            case MotionEvent.ACTION_UP:
                mX = event.getX();
                mY = event.getY();
                break;
        }
        return super.onTouchEvent(event);

    }

    /**
     * 通过 Resource资源获得屏幕像素比
     *
     * 像素px = 屏幕像素比 * 具体值
     * @param dp
     * @return
     */
    private int spToPx(float dp){
        // 获得屏幕像素比
        float scale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int)(scale*dp+0.5f);
    }

    @Override
    public void onClick(View v) {
        if (!isClick){
            return;
        }
        Toast.makeText(getContext(), getWhichIndex(), Toast.LENGTH_SHORT).show();
    }


    private String getWhichIndex(){
        int indexX = (int) (mX / innerWidth);
        if (indexX >= 7) {
            indexX = 6;
        }
        int indexY = (int) mY / itemHeight;
        int position = indexY * 7 + indexX;// 选择项
        if (position >= 0 && position < dataLength)
            return mData.get(position);
        return "";
    }
}
