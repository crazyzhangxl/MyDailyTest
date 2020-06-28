package com.zxl.mydailytest.activities;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.RelativeLayout;

import androidx.viewpager.widget.PagerAdapter;

/**
 * @author crazyZhangxl on 2018/12/25.
 * Describe:
 */
public class EmotionViewPagerAdapter extends PagerAdapter {
    /**
     * 页面数量------
     */
    int mPageCount = 0;
    /**
     * 标签索引--------
     */
    int mTabPosi = 0;
    /**
     * 宽高
     */
    private int mEmotionLayoutWidth;
    private int mEmotionLayoutHeight;

    /**
     * viewpager构造函数-----
     * @param tabPosi
     * @param emotionLayoutWidth
     * @param emotionLayoutHeight
     */
    public EmotionViewPagerAdapter(int tabPosi, int emotionLayoutWidth, int emotionLayoutHeight) {
        mTabPosi = tabPosi;
        mEmotionLayoutWidth = emotionLayoutWidth;
        mEmotionLayoutHeight = emotionLayoutHeight;
        mPageCount = (int) Math.ceil(EmotionDataManager.getDisplayCount()/1.0f/EmotionDataManager.SIZE);
        Log.e("111", "EmotionViewPagerAdapter: "+mPageCount);
    }

    /**
     * 返回pagerAdapter的数量
     * @return
     */
    @Override
    public int getCount() {
        return mPageCount == 0 ? 1:mPageCount;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    /**
     * 返回对象
     * @param container
     * @param position
     * @return
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // 在viewpager里面创建gridView对象 -------
        Context context = container.getContext();
        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.setGravity(Gravity.CENTER);
        GridView gridView = new GridView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        gridView.setLayoutParams(layoutParams);
        gridView.setGravity(Gravity.CENTER);
        // 标记自己是第几页 ------------------ 牛逼了,卧槽
        gridView.setTag(position);
        // 7列
        gridView.setNumColumns(EmotionDataManager.ROW);
        gridView.setAdapter(new GridAdapter(context,mEmotionLayoutHeight,mEmotionLayoutWidth,position*EmotionDataManager.SIZE));
        relativeLayout.addView(gridView);
        // 很重要    -----
        container.addView(relativeLayout);
        // 返回布局  -----
        return relativeLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
