package com.zxl.mydailytest.scroll.conflict;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.ListView;

/**
 * Created by apple on 2019-11-08.
 * description:
 */
public class ConflictBListView extends ListView {
    private float downX,downY;
    private int touchSlop;

    public ConflictBListView(Context context) {
        super(context);
        init(context);

    }

    public ConflictBListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public ConflictBListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        touchSlop =  ViewConfiguration.get(context).getScaledTouchSlop();
        Log.e("滑动", "init: "+touchSlop );
    }


    // 上下滑动的问题


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                downY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float distanceX = ev.getX() - downX;
                float distanceY = ev.getY() -downY;
                if (Math.abs(distanceY) > Math.abs(distanceX)){
                    Log.e("滑动", "distance : "+distanceY );
                    if (distanceY > 0 && (Math.abs(distanceY) > touchSlop)){
                        // 下拉
                        if (hasScrollToTop()){
                            Log.e("滑动", "在顶部" );
                            getParent().requestDisallowInterceptTouchEvent(false);
                        }else {
                            getParent().requestDisallowInterceptTouchEvent(true);
                        }

                    }else {
                        if (Math.abs(distanceY) > touchSlop){
                            if (hasScrollToBottom()){
                                Log.e("滑动", "在底部" );
                                getParent().requestDisallowInterceptTouchEvent(false);
                            }else {
                                getParent().requestDisallowInterceptTouchEvent(true);
                            }
                        }

                    }

                }
                break;
        }

        return super.dispatchTouchEvent(ev);
    }

    /**
     * listview 首个可见item为第一个 且距离父组件为0
     * @return
     */
    private boolean hasScrollToTop(){
        if (getFirstVisiblePosition() == 0){
            View view = getChildAt(0);
            if (view != null && view.getTop() == 0){
                return true;
            }
        }
        return  false;
    }

    private boolean hasScrollToBottom(){
        if (getLastVisiblePosition() == getCount() -1){
            View view = getChildAt(getCount() - 1);
            if (view != null && view.getBottom() == getHeight()){
                return true;
            }
        }
        return false;
    }

}
