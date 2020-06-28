package com.zxl.mydailytest.wuba;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * @author crazyZhangxl on 2018/10/17.
 * Describe:
 */
public class ViewPagerEx extends ViewPager
{
    public boolean noScroll = false;

    public ViewPagerEx(Context paramContext)
    {
        super(paramContext);
    }

    public ViewPagerEx(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
    {
        if (noScroll){
            return false;
        }
        return super.onInterceptTouchEvent(paramMotionEvent);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (noScroll){
            return false;
        }
        return super.onTouchEvent(ev);
    }

    public void setNoScroll(boolean paramBoolean)
    {
        this.noScroll = paramBoolean;
    }
}