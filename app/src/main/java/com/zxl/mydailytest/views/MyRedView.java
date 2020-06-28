package com.zxl.mydailytest.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.annotation.SuppressLint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author zxl on 2018/7/24.
 *         discription:
 */

public class MyRedView extends View {
    private Context mContext;
    private Paint mPaint;
    public MyRedView(Context context) {
        this(context,null);
    }

    public MyRedView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public MyRedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        Log.e("画","--------执行了啊");
        mContext = context;
        mPaint = new Paint();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));
    }

    private int measureHeight(int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        switch (mode){
            case MeasureSpec.AT_MOST:
                size = 200;
                break;
            case MeasureSpec.EXACTLY:
                // 精确模式咯,Match Size 就是这么大
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
            default:
                break;
        }
        return size;
    }

    private int measureWidth(int widthMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        switch (mode){
            case MeasureSpec.AT_MOST:
                size = 400;
                break;
            case MeasureSpec.EXACTLY:
                // 精确模式咯,Match Size 就是这么大
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
            default:
                break;
        }
        return size;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);
        canvas.drawRect(20,20,120,50,mPaint);
    }
}
