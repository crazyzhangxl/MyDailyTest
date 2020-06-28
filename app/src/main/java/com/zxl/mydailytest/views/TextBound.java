package com.zxl.mydailytest.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * @author zxl on 2018/8/15.
 *         discription:
 */

public class TextBound extends AppCompatTextView {
    /**
     * 这个是直接new出来用的
     * @param context
     */
    public TextBound(Context context) {
        super(context);
    }

    /**
     * 这个就是在 xml中
     * @param context
     * @param attrs
     */
    public TextBound(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN){
            int x = (int )event.getX();
            Log.e("坐标","-------"+x+"-----------");
        }
        return super.onTouchEvent(event);
    }
}
