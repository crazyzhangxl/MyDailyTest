package com.zxl.mydailytest;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * @author zxl on 2018/7/18.
 *         discription:
 */

public class MyScrollView extends ScrollView {
    public ScrollViewListener mScrollViewListener;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface ScrollViewListener{
        void onScroll(MyScrollView myScrollView,int x,int y);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener){
        this.mScrollViewListener = scrollViewListener;
    }

    public void removeScrollViewListener(){
        this.mScrollViewListener = null;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mScrollViewListener != null){
            mScrollViewListener.onScroll(this,l,t);
        }
    }
}
