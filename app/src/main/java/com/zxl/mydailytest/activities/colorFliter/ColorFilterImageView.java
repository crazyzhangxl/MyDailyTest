package com.zxl.mydailytest.activities.colorFliter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * des:实现图像根据按下抬起动作变化颜色
 * on 2016.07.11:14
 * @author zxl
 */
public class ColorFilterImageView extends AppCompatImageView
        implements OnTouchListener {
    public ColorFilterImageView(Context context) {
        super(context);
        init();
    }

    public ColorFilterImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ColorFilterImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setOnTouchListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            // 按下时图像变灰
            case MotionEvent.ACTION_DOWN:
                Log.e("事件","摁下");
                setColorFilter(Color.GRAY, Mode.MULTIPLY);
                break;
            // 手指离开或取消操作时恢复原色
            case MotionEvent.ACTION_UP:
                Log.e("事件","抬起");
                setColorFilter(Color.TRANSPARENT);
            case MotionEvent.ACTION_CANCEL:
                Log.e("事件","取消");
                setColorFilter(Color.TRANSPARENT);
                break;
            default:
                break;
        }
        return false;
    }
}
