package com.zxl.mydailytest.scroll.conflict;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by apple on 2019-11-07.
 * description:
 */
public class IMyScrollView extends ScrollView {


    public IMyScrollView(Context context) {
        super(context);
    }

    public IMyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public IMyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
