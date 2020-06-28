package com.zxl.mydailytest.activities;

import android.content.Context;
import android.util.Log;

import com.zxl.mydailytest.MyApplication;
import com.zxl.mydailytest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author crazyZhangxl on 2018/12/25.
 * Describe:
 */
public class EmotionDataManager {

    private static final List<Integer> mDefaultEntries = new ArrayList<>();
    /**
     * 长宽高
     */
    public static final int COLUMN = 3;
    public static final int ROW = 7;
    public static final int SIZE = 21;
    static {
        // ------- 静态加载数据/或者也可以在application里面加载 ------
        Log.e("数据加载", "static initializer: ");
        Context context = MyApplication.mContext;
        load(context);
    }

    public static List<Integer> getDefaultEntries() {
        return mDefaultEntries;
    }

    private static void load(Context context) {
        mDefaultEntries.clear();
        mDefaultEntries.add(R.mipmap.emoji_00);
        mDefaultEntries.add(R.mipmap.emoji_01);
        mDefaultEntries.add(R.mipmap.emoji_02);
        mDefaultEntries.add(R.mipmap.emoji_03);
        mDefaultEntries.add(R.mipmap.emoji_04);
        mDefaultEntries.add(R.mipmap.emoji_05);
        mDefaultEntries.add(R.mipmap.emoji_06);
        mDefaultEntries.add(R.mipmap.emoji_07);
        mDefaultEntries.add(R.mipmap.emoji_08);
        mDefaultEntries.add(R.mipmap.emoji_09);
        mDefaultEntries.add(R.mipmap.emoji_10);
        mDefaultEntries.add(R.mipmap.emoji_11);
        mDefaultEntries.add(R.mipmap.emoji_12);
        mDefaultEntries.add(R.mipmap.emoji_13);
        mDefaultEntries.add(R.mipmap.emoji_14);
        mDefaultEntries.add(R.mipmap.emoji_15);
        mDefaultEntries.add(R.mipmap.emoji_16);
        mDefaultEntries.add(R.mipmap.emoji_17);
        mDefaultEntries.add(R.mipmap.emoji_18);
        mDefaultEntries.add(R.mipmap.emoji_19);
        mDefaultEntries.add(R.mipmap.emoji_20);
        /* ------ */
        mDefaultEntries.add(R.mipmap.emoji_15);
        mDefaultEntries.add(R.mipmap.emoji_16);
        mDefaultEntries.add(R.mipmap.emoji_17);
        mDefaultEntries.add(R.mipmap.emoji_18);
        mDefaultEntries.add(R.mipmap.emoji_19);
        mDefaultEntries.add(R.mipmap.emoji_20);
        mDefaultEntries.add(R.mipmap.emoji_00);
        mDefaultEntries.add(R.mipmap.emoji_01);
        mDefaultEntries.add(R.mipmap.emoji_02);
        mDefaultEntries.add(R.mipmap.emoji_03);
        mDefaultEntries.add(R.mipmap.emoji_04);
        mDefaultEntries.add(R.mipmap.emoji_05);
        mDefaultEntries.add(R.mipmap.emoji_06);
        mDefaultEntries.add(R.mipmap.emoji_07);
        mDefaultEntries.add(R.mipmap.emoji_08);
        mDefaultEntries.add(R.mipmap.emoji_09);
        mDefaultEntries.add(R.mipmap.emoji_10);
        mDefaultEntries.add(R.mipmap.emoji_11);
        mDefaultEntries.add(R.mipmap.emoji_12);
        mDefaultEntries.add(R.mipmap.emoji_13);
        mDefaultEntries.add(R.mipmap.emoji_14);
        /* ------ */
        mDefaultEntries.add(R.mipmap.emoji_15);
        mDefaultEntries.add(R.mipmap.emoji_16);
        mDefaultEntries.add(R.mipmap.emoji_17);
        mDefaultEntries.add(R.mipmap.emoji_18);
        mDefaultEntries.add(R.mipmap.emoji_19);
        mDefaultEntries.add(R.mipmap.emoji_20);
        mDefaultEntries.add(R.mipmap.emoji_00);
        mDefaultEntries.add(R.mipmap.emoji_01);
        mDefaultEntries.add(R.mipmap.emoji_02);
    }

    public static final int getDisplayCount() {
        return mDefaultEntries.size();
    }

    public static int dpToPx(int dp){
        float density = MyApplication.mContext.getResources().getDisplayMetrics().density;
        return Math.round(density*dp);
    }
}
