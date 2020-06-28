package com.zxl.mydailytest.activities;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * @author crazyZhangxl on 2018/12/25.
 * Describe:
 */
public class GridAdapter extends BaseAdapter {
    private Context mContext;
    private int mHeight;
    private int mWidth;
    private int startCount;
    private  float mPerWidth = 0;
    private  float mPerHeight = 0;
    private  float mIvSize = 0;

    public GridAdapter(Context context, int height, int width, int startCount) {
        mContext = context;
        //减去底部的tab高度、小圆点的高度才是viewpager的高度，再减少30dp是让表情整体的顶部和底部有个外间距
        mHeight = height - EmotionDataManager.dpToPx(35 + 26 + 50);
        mWidth = width;
        this.startCount = startCount;
        mPerHeight = mHeight*1f/EmotionDataManager.COLUMN;
        mPerWidth = mWidth*1f/EmotionDataManager.ROW;
        mIvSize = Math.min(mPerHeight*0.6f,mPerWidth*0.6f);
    }

    /**
     * 返回数量
     * 返回剩余数量和3行数量的最小值
     * @return
     */
    @Override
    public int getCount() {
        int count = EmotionDataManager.getDisplayCount() - startCount;
        count = Math.min(count,EmotionDataManager.SIZE);
        return count;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return startCount+position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout relativeLayout = new RelativeLayout(mContext);
        relativeLayout.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,(int)mPerHeight));
        ImageView imageView = new ImageView(mContext);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        // 水品居中
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParams.width = (int) mIvSize;
        layoutParams.height = (int) mIvSize;
        // 前面用到的count
        imageView.setLayoutParams(layoutParams);
        imageView.setBackgroundResource(EmotionDataManager.getDefaultEntries().get(position+startCount));
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(imageView);
        // 返回rl
        return relativeLayout;
    }
}
