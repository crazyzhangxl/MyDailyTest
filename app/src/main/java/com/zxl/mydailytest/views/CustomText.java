package com.zxl.mydailytest.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author zxl on 2018/7/21.
 *         discription:
 */

public class CustomText extends View{
    private Paint mPaint;
    private Rect mRectBounds;
    private String mText;
    private int mClipColor = Color.parseColor("#00ff00");

    public CustomText(Context context) {
        super(context);
        init(context);
    }

    public CustomText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mRectBounds = new Rect();
        //单位为px 这里应该屏幕适配
        mPaint.setTextSize(32);
        mPaint.setColor(Color.parseColor("#ff0000"));
        setPadding(10,0,10,0);
    }


    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
        requestLayout();
    }

    public void setNext(){
        invalidate();
    }

    /**
     * 设置属性后进行刷新
     */
    private void measureTextBounds() {
        mPaint.getTextBounds(mText,0,mText == null ? 0:mText.length(),mRectBounds);
        Log.e("矩阵","宽 = "+mRectBounds.width()+"  高 = "+mRectBounds.height());
    }

    /**
     * 测量
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureTextBounds();
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));
    }

    /**
     * 对于测量模式:总结一下
     *
     * LayoutParams.Match_Parent/固定大小  :  精确模式 Exactly
     * LayoutParams.Wrap_Parent    : 最大模式 AT_MOST 申请的空间 = Match_parent,
     *                               所以需要做处理,让其宽高为真实值,所以明显取最小值
     * @param heightMeasureSpec
     * @return
     */
    private int measureHeight(int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int result = size;
        switch (mode){
            case MeasureSpec.AT_MOST:
                int height = mRectBounds.height() + getPaddingTop() + getPaddingBottom();
                result = Math.min(height,size);
                break;
            case MeasureSpec.EXACTLY:
                // 牛皮 确实是精确模式
                break;
            case MeasureSpec.UNSPECIFIED:
                result = mRectBounds.height() + getPaddingTop() + getPaddingBottom();
                break;
            default:
                break;
        }
        return result;
    }

    private int measureWidth(int widthMeasureSpec) {
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int result = size;
        switch (mode){
            case MeasureSpec.AT_MOST:
                int width = mRectBounds.width() + getPaddingLeft() + getPaddingRight();
                result = Math.min(width,size);
                break;
            case MeasureSpec.EXACTLY:
                break;
            case MeasureSpec.UNSPECIFIED:
                result = mRectBounds.width() + getPaddingLeft() + getPaddingRight();
                break;
            default:
                break;
        }

        return result;
    }

    /**
     * 绘制文本(/DrawText的细节)
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        // 水平居中
        int x = (getWidth() - mRectBounds.width()) / 2;
        // 垂直居中

        // top    即字符顶部到基线的最大值
        // bottom 即字符底部到基线的最大值
        Paint.FontMetrics fontMetrics = mPaint.getFontMetrics();
        int y = (int) ((getHeight() - (fontMetrics.top + fontMetrics.bottom))/2);
        canvas.drawText(mText,x,y,mPaint);

        canvas.save();
        boolean isOk = false;
        if (isOk) {
            canvas.clipRect(0, 0, getWidth() * 0.8f, getHeight());
        } else {
            canvas.clipRect(getWidth() * 0.8f, 0, getWidth(), getHeight());
        }
        mPaint.setColor(mClipColor);
        canvas.drawText(mText, x, y, mPaint);
        canvas.restore();
    }
}
