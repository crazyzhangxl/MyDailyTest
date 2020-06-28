package com.zxl.mydailytest.wuba;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.viewpager.widget.ViewPager;

import com.zxl.mydailytest.R;


/**
 * @author crazyZhangxl on 2018/10/17.
 * Describe: 跳跃式的指示器 -------------
 */
public class JumpCPagerIndicator extends View {
    private int circleCount;
    private float moveX;
    private float x, y;
    private float radius;
    private float strokeWidth;
    private float divideWidth;
    private int fillColor;
    private int emptyColor;
    private float ratio;
    private Paint emptyPaint;
    private Paint fillPaint;
    private ViewPager mViewPager;
    private boolean isCanShow = false;
    private int mSelectedPostion;
    public JumpCPagerIndicator(Context context) {
        super(context);
    }

    public JumpCPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.JumpCPagerIndicator);
        radius = a.getDimension(R.styleable.JumpCPagerIndicator_circleRadius, 3);
        strokeWidth = a.getDimension(R.styleable.JumpCPagerIndicator_circleStroke, 1);
        divideWidth = a.getDimension(R.styleable.JumpCPagerIndicator_circleDivide, 15);
        fillColor = a.getColor(R.styleable.JumpCPagerIndicator_circleFillColor, 0xffffff);
        emptyColor = a.getColor(R.styleable.JumpCPagerIndicator_circleEmptyColor, 0x66ffffff);

        init(context);
    }

    public void  setViewPager(ViewPager viewPager){
        mViewPager = viewPager;
        circleCount = viewPager.getAdapter().getCount();
        invalidate();
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                moveCircle(position,positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }



    private void init(Context context) {
        moveX = strokeWidth + radius;
        x = strokeWidth + radius;
        y = strokeWidth + radius;

        emptyPaint = new Paint();
        emptyPaint.setStrokeWidth(strokeWidth);
        emptyPaint.setStyle(Paint.Style.FILL);
        emptyPaint.setAntiAlias(true);
        emptyPaint.setColor(emptyColor);

        fillPaint = new Paint();
        fillPaint.setStrokeWidth(strokeWidth);
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setAntiAlias(true);
        fillPaint.setColor(fillColor);

        WindowManager windowManager = (WindowManager)  context.getSystemService(Context.WINDOW_SERVICE);
        int screentWidth = windowManager.getDefaultDisplay().getWidth();
        ratio = (radius * 2 + strokeWidth * 2 + divideWidth) / screentWidth;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        float width, height;
        width = circleCount * (radius * 2 + strokeWidth * 2) + (circleCount - 1) * divideWidth;
        height = radius * 2 + strokeWidth * 2;
        setPadding(0, 0, 0, 0);
        setMeasuredDimension((int)width, (int)height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 初始化的时候不绘制
        if (circleCount == -1){
            return;
        }

        float incremental = radius * 2 + strokeWidth * 2 + divideWidth;
        if (isCanShow){
            for (int i = 0; i < circleCount; i++) {
                if (mSelectedPostion != i) {
                    canvas.drawCircle(x + incremental * i, y, radius, emptyPaint);
                }else {
                    canvas.drawCircle(x + incremental * i, y, radius, fillPaint);
                }
            }

        }else {
            canvas.drawCircle(moveX, y, radius, fillPaint);
            for (int i = 0; i < circleCount; i++) {
                canvas.drawCircle(x + incremental * i, y, radius, emptyPaint);
            }
        }
    }

    private void moveCircle(int position, int location) {
        if (location == 0){
            isCanShow = true;
        }else {
            isCanShow = false;
        }
        mSelectedPostion = position;
        float incremental = radius * 2 + strokeWidth * 2 + divideWidth;
        float firstLocation = strokeWidth + radius;
        moveX = firstLocation +  position * incremental +  location * ratio;
        invalidate();
    }

}
