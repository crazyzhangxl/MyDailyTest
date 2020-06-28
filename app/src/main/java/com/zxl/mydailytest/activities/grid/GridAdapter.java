package com.zxl.mydailytest.activities.grid;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import java.util.ArrayList;
import java.util.List;

/**
 * @author crazyZhangxl on 2018/12/24.
 * Describe:
 */
public class GridAdapter extends BaseAdapter {
    private Context mContext;
    private int gridHeight;
    private int gridWidth;
    /**
     * 用于设置小rl的宽高
     */
    private float mPreWidth;
    private float mPreHeight;
    private float ivSize;
    /**
     * 3行
     */
    private static final int SHOW_ROW = 3;
    /**
     * 7列
     */
    private static final int SHOW_COLUMN = 7;
    private List<Integer> mImageResList = new ArrayList<>();


    public void setNewData(List<Integer> imageResList){
        mImageResList.clear();
        mImageResList.addAll(imageResList);
        notifyDataSetChanged();
    }
    /**
     * 构造函数 --------
     * @param context
     * @param gridHeight
     * @param gridWidth
     */
    public GridAdapter(Context context, int gridHeight, int gridWidth) {
        mContext = context;
        this.gridHeight = gridHeight;
        this.gridWidth = gridWidth;
        mPreHeight = gridHeight *1f/SHOW_ROW;
        mPreWidth = gridWidth * 1f/SHOW_COLUMN;
        float width = mPreWidth*0.6f;
        float height = mPreHeight*0.6f;
        ivSize = Math.min(width,height);

    }

    @Override
    public int getCount() {
        if (mImageResList != null){
            return mImageResList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        // ID ----
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RelativeLayout relativeLayout = new RelativeLayout(mContext);
        // 对于外围包围的宽还是要设置其match,当然可以给其设置高属性
        relativeLayout.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,(int) mPreHeight));
        ImageView imageView = new ImageView(mContext);
        imageView.setBackgroundResource(mImageResList.get(position));
        // 设置iv的宽高属性
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout
                .LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        layoutParams.width = (int) ivSize;
        layoutParams.height = (int) ivSize;
        imageView.setLayoutParams(layoutParams);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(imageView);
        return relativeLayout;
    }
}
