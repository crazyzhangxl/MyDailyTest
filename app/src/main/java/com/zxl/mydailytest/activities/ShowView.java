package com.zxl.mydailytest.activities;

import android.content.Context;
import android.annotation.SuppressLint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.zxl.mydailytest.R;


/**
 * @author crazyZhangxl on 2018/12/25.
 * Describe:
 */
public class ShowView extends LinearLayout {
    private Context mContext;
    public ShowView(Context context) {
        this(context,null);
    }

    public ShowView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public ShowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.emotion_layout,this,true);
    }
}
