package com.zxl.mydailytest;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

import androidx.appcompat.widget.AppCompatButton;

/**
 * @author zxl on 2018/7/19.
 *         discription:
 */

public class MyButton extends AppCompatButton {
    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.e("事件分发","子View dispatchTouchEvent");
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Log.e("事件分发","子View onTouchEvent 默认返回: "+super.onTouchEvent(event) +" 触摸状态 "+event.getAction() );
        // 哇 竟然不响应点击事件了
        // 必须得调用 super.onTouchEvent(x)
        //          然后才会进入 performClick()
        super.onTouchEvent(event);
        return true;

        // 是否该view进行消费
        // 如果返回的是false 不会进行其他事件的处理
    }

}
