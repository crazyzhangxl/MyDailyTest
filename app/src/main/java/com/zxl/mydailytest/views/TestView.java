package com.zxl.mydailytest.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author zxl on 2018/9/17.
 *         discription: 测试View
 */

public class TestView extends View {
    private Paint backGroundPaint;
    private Paint rectPaint;

    public TestView(Context context) {
       this(context,null);
    }

    public TestView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public TestView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {

        rectPaint = new Paint();
        rectPaint.setColor(Color.BLACK);
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setStrokeWidth(10);
        rectPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GREEN);
        Rect rect = new Rect(10/2,10/2,40,40);
        canvas.drawRect(rect,rectPaint);
    }
}
